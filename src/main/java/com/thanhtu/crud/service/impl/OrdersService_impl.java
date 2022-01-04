package com.thanhtu.crud.service.impl;


import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.*;
import com.thanhtu.crud.model.mapper.CartIdKeyMapper;
import com.thanhtu.crud.model.mapper.OrdersDetailMapper;
import com.thanhtu.crud.model.mapper.OrdersMapper;
import com.thanhtu.crud.model.request.*;
import com.thanhtu.crud.repository.*;
import com.thanhtu.crud.service.OrdersService;
import com.thanhtu.crud.service.email.MailSender;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@RequiredArgsConstructor
public class OrdersService_impl implements OrdersService {
    @Autowired
    PaypalService_impl paypalService;
    @Autowired
    OrdersRepository orderRepo;
    @Autowired
    OrdersDetailRepository ordersDetailRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    CartRepository cartRepo;
    @Autowired CustomerRepository customerRepo;
    private final MailSender mailSender;


    @Override
    public Page<OrdersEntity> getListOrderByStatus(String status, Pageable page) {
        return orderRepo.findOrdersEntityByStatusOrderOrderByCreateDateDesc(status,page);
    }

    @Override
    public OrdersDto updateOrders(int id, OrdersUpdateRequest ordersUpdateRequest) {
        OrdersEntity ordersDelete=orderRepo.findOrdersEntityByOrderIdAndStatusOrder(id,"Đã hủy");
        if(ordersDelete!=null)
        {
            throw new NotFoundException("Đã hủy đơn hàng với id: "+id);
        }
        OrdersEntity ordersEntity=orderRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại đơn hàng với id: "+id));
        OrdersEntity orders=orderRepo.save(OrdersMapper.toUpdateOrders(ordersEntity,ordersUpdateRequest));
        return OrdersMapper.toOrdersDto(orders);
    }

    @Override
    public void approvalOrders(OrdersUpdateStatusRequest ordersUpdateStatusRequest) {
        for(OrdersIdDto orderId:ordersUpdateStatusRequest.getList())
        {
            OrdersEntity ordersEntity=orderRepo.findById(orderId.getOrdersId()).orElseThrow(()-> new NotFoundException("Không tồn tại đơn hàng với id: "+orderId.getOrdersId()));
            ordersEntity.setStatusOrder("Đã duyệt");
            orderRepo.save(ordersEntity);
        }
    }

    @Override
    public List<OrdersEntity> getListOrderByStatus(String status) {
        List<OrdersEntity> list=orderRepo.findOrdersEntityByStatusOrder(status);
        return list;
    }

    @Override
    public void cancelOrder(Integer id) {
        OrdersEntity order=orderRepo.findOrdersEntityByOrderId(id);
        if(order==null)
        {
            throw new NotFoundException("Không tìm thấy đơn hàng có id: "+id);
        }
        order.setStatusOrder("Đã hủy");
        List<OrderDetailEntity> listCancel= ordersDetailRepo.findOrderDetailEntityByOrdersEntity(order);
        for(OrderDetailEntity orderDetail:listCancel)
        {
            orderDetail.setIsDelete("YES");
            ProductEntity product= productRepo.findProductEntityByProductIdAndIsDelete(orderDetail.getId().getProductId(),"NO");
            if(product!=null)
            {
                int quantityUpdate=product.getQuantity()+orderDetail.getQuantity();
                product.setQuantity(quantityUpdate);
                productRepo.save(product);
            }
            ordersDetailRepo.save(orderDetail);
        }
        orderRepo.save(order);
    }

    @Override
    public OrdersDto createOrders(OrderCreateRequest orderCreateRequest) {
        List<ProductToOrder> productToOrderList=new ArrayList<ProductToOrder>();
        productToOrderList=orderCreateRequest.getList();
        for(ProductToOrder productToOrder:productToOrderList)
        {
            ProductEntity product=productRepo.findProductEntityByProductIdAndIsDelete(productToOrder.getProductId(),"NO");
            if(product==null)
            {
                throw new NotFoundException(""+product.getProductName()+" không còn bán nữa");
            }
            if(product.getQuantity()<productToOrder.getQuantity())
            {
                throw new NotFoundException(""+product.getProductName()+" không đủ số lượng. Chỉ còn "+product.getQuantity());
            }
        }
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(orderCreateRequest.getCustomerId(),"NO");
        OrdersEntity orderCreate=orderRepo.save(OrdersMapper.toCreateOrders(orderCreateRequest,customer));
        for(ProductToOrder productToOrder:productToOrderList)
        {
            ProductEntity product=productRepo.findProductEntityByProductIdAndIsDelete(productToOrder.getProductId(),"NO");
            CartEntity cartDelete= cartRepo.findCartEntityById(CartIdKeyMapper.toCartIdKey(customer,product));
            cartRepo.delete(cartDelete);
            ordersDetailRepo.save(OrdersDetailMapper.toOrderDetailEntity(productToOrder,orderCreate,product));
            int quantityUpdate=product.getQuantity()-productToOrder.getQuantity();
            product.setQuantity(quantityUpdate);
            productRepo.save(product);
        }
        String subject = "Đơn hàng #" + orderCreate.getOrderId();
        String template = "order-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", orderCreate);
        attributes.put("fullname",customer.getFullnameCustomer());
        mailSender.sendMessageHtml(orderCreate.getCustomerEntity().getGmailCustomer(), subject, template, attributes);
        return OrdersMapper.toOrdersDto(orderCreate);
    }
    @Override
    public OrdersDto createOrdersPaypal(OrderCreateRequest orderCreateRequest)
    {
        List<ProductToOrder> productToOrderList=new ArrayList<ProductToOrder>();
        productToOrderList=orderCreateRequest.getList();
        for(ProductToOrder productToOrder:productToOrderList)
        {
            ProductEntity product=productRepo.findProductEntityByProductIdAndIsDelete(productToOrder.getProductId(),"NO");
            if(product==null)
            {
                throw new NotFoundException(""+product.getProductName()+" không còn bán nữa");
            }
            if(product.getQuantity()<productToOrder.getQuantity())
            {
                throw new NotFoundException(""+product.getProductName()+" không đủ số lượng. Chỉ còn "+product.getQuantity());
            }
        }
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(orderCreateRequest.getCustomerId(),"NO");
        OrdersEntity orders=OrdersMapper.toCreateOrders(orderCreateRequest,customer);
        orders.setNote("Chưa thanh toán");
        OrdersEntity orderCreate=orderRepo.save(orders);
        for(ProductToOrder productToOrder:productToOrderList)
        {
            ProductEntity product=productRepo.findProductEntityByProductIdAndIsDelete(productToOrder.getProductId(),"NO");
            CartEntity cartDelete= cartRepo.findCartEntityById(CartIdKeyMapper.toCartIdKey(customer,product));
            cartRepo.delete(cartDelete);
            ordersDetailRepo.save(OrdersDetailMapper.toOrderDetailEntity(productToOrder,orderCreate,product));
            int quantityUpdate=product.getQuantity()-productToOrder.getQuantity();
            product.setQuantity(quantityUpdate);
            productRepo.save(product);
        }
        return OrdersMapper.toOrdersDto(orderCreate);
    }

    @Override
    public void confirmPaymentAndSendMail(int orderId) {
        OrdersEntity ordersEntity=orderRepo.findOrdersEntityByOrderId(orderId);
        ordersEntity.setNote("Đã thanh toán");
        orderRepo.save(ordersEntity);
        String subject = "Đơn hàng #" + orderId;
        String template = "order-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", ordersEntity);
        attributes.put("fullname",ordersEntity.getCustomerEntity().getFullnameCustomer());
        mailSender.sendMessageHtml(ordersEntity.getCustomerEntity().getGmailCustomer(), subject, template, attributes);
    }

    @Override
    public void sendEmailOrder() {

    }

    @Override
    public List<OrderDetailView> getOrderDetailByCustomerIdAndStatus(int id,OrdersStatusRequest ordersStatusRequest) {
        List<OrderDetailView> orderDetailViewList=new ArrayList<OrderDetailView>();
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(id,"NO");
        List<OrdersEntity> ordersEntities=orderRepo.findOrdersEntityByCustomerEntityAndStatusOrder(customer,ordersStatusRequest.getStatusOrder());
        for(OrdersEntity ordersEntity:ordersEntities)
        {
            OrderDetailView orderDetailView= new OrderDetailView();
            orderDetailView.setOrderId(ordersEntity.getOrderId());
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
            String formattedDate = ordersEntity.getCreateDate().toLocalDateTime().format(myFormatObj);
            orderDetailView.setCreateDate(formattedDate);
            orderDetailView.setAmount(ordersEntity.getTotalAmount());
            List<OrderDetailEntity> listOrderDetail=ordersDetailRepo.findOrderDetailEntityByOrdersEntity(ordersEntity);
            List<ProductOrder> listProductOrder=new ArrayList<ProductOrder>();
            for(OrderDetailEntity orderDetailEntity:listOrderDetail)
            {
                ProductOrder productOrder=new ProductOrder();
                productOrder.setOrderId(orderDetailEntity.getId().getOrderId());
                productOrder.setProducId(orderDetailEntity.getId().getProductId());
                productOrder.setProductName(orderDetailEntity.getProductEntity().getProductName());
                productOrder.setQuantity(orderDetailEntity.getQuantity());
                listProductOrder.add(productOrder);
            }
            orderDetailView.setListProduct(listProductOrder);
            orderDetailViewList.add(orderDetailView);
        }
        return orderDetailViewList;
    }

    @Override
    public void orderDelivered(OrdersUpdateStatusRequest ordersUpdateStatusRequest) {
        for(OrdersIdDto orderId:ordersUpdateStatusRequest.getList())
        {
            OrdersEntity ordersEntity=orderRepo.findById(orderId.getOrdersId()).orElseThrow(()-> new NotFoundException("Không tồn tại đơn hàng với id: "+orderId.getOrdersId()));
            ordersEntity.setStatusOrder("Đã giao");
            ordersEntity.setNote("Đã thanh toán");
            orderRepo.save(ordersEntity);
        }
    }

    @Override
    public List<ProductToReview> getOrderDetailByCustomerToReview(int id, String statusOrder) {
        List<ProductToReview> productToReviewList=new ArrayList<ProductToReview>();
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(id,"NO");
        List<OrdersEntity> ordersEntities=orderRepo.findOrdersEntityByCustomerEntityAndStatusOrder(customer,statusOrder);
        for(OrdersEntity ordersEntity:ordersEntities)
        {
            List<OrderDetailEntity> listOrderDetail=ordersDetailRepo.findOrderDetailEntityByOrdersEntity(ordersEntity);
            for(OrderDetailEntity orderDetailEntity:listOrderDetail)
            {
                ProductToReview productToReview= new ProductToReview();
                productToReview.setOrderId(ordersEntity.getOrderId());
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
                String formattedDate = ordersEntity.getCreateDate().toLocalDateTime().format(myFormatObj);
                productToReview.setCreateOrders(formattedDate);
                productToReview.setProductId(orderDetailEntity.getId().getProductId());
                productToReview.setProductName(orderDetailEntity.getProductEntity().getProductName());
                productToReview.setQuantity(orderDetailEntity.getQuantity());
                productToReview.setIsReview(orderDetailEntity.getIsReview());
                productToReviewList.add(productToReview);
            }
        }
        Collections.sort(productToReviewList, new Comparator<ProductToReview>() {
            @Override
            public int compare(ProductToReview o1, ProductToReview o2) {
                if(o1.getIsReview().equals("NO") && o2.getIsReview().equals("YES"))
                {
                    return -1;
                }
                else if(o1.getIsReview().equals("NO") && o2.getIsReview().equals("YES")){
                    return 1;
                }
                else{
                    if(o1.getOrderId()< o2.getOrderId())
                    {
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        });
        return productToReviewList;
    }

}
