package com.thanhtu.crud.service.impl;


import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.OrderDetailView;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.dto.OrdersIdDto;
import com.thanhtu.crud.model.dto.ProductToOrder;
import com.thanhtu.crud.model.mapper.DeliveryMapper;
import com.thanhtu.crud.model.mapper.OrdersDetailMapper;
import com.thanhtu.crud.model.mapper.OrdersMapper;
import com.thanhtu.crud.model.request.*;
import com.thanhtu.crud.repository.*;
import com.thanhtu.crud.service.OrdersService;
import com.thanhtu.crud.service.email.MailSender;
import com.thanhtu.crud.utils.Utils;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    DeliveryRepository deliveryRepo;
    @Autowired
    ShipperRepository shipperRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired CustomerRepository customerRepo;
    private final MailSender mailSender;


    @Override
    public List<OrdersEntity> getListOrderByStatus(OrdersStatusRequest ordersStatusRequest) {
        List<OrdersEntity> list=orderRepo.findOrdersEntityByStatusOrder(ordersStatusRequest.getStatusOrder());
        return list;
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
    public void assignOrders(List<OrdersAssignRequest> list) {
        for(OrdersAssignRequest ordersAssignRequest:list)
        {
            OrdersEntity ordersEntity=orderRepo.findById(ordersAssignRequest.getOrderId()).orElseThrow(()-> new NotFoundException("Không tồn tại đơn hàng với id: "+ordersAssignRequest.getOrderId()));
            ordersEntity.setStatusOrder("Đang giao");
            ShipperEntity shipperEntity=shipperRepo.findById(ordersAssignRequest.getShipperId()).orElseThrow(()-> new NotFoundException("Không tồn tại shipper với id: "+ordersAssignRequest.getShipperId()));
            deliveryRepo.save(DeliveryMapper.toDeliveryEntity(ordersAssignRequest,shipperEntity,ordersEntity));
        }
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
            ordersDetailRepo.save(OrdersDetailMapper.toOrderDetailEntity(productToOrder,orderCreate,product));
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
        orders.setNote("Đã thanh toán");
        OrdersEntity orderCreate=orderRepo.save(orders);
        for(ProductToOrder productToOrder:productToOrderList)
        {
            ProductEntity product=productRepo.findProductEntityByProductIdAndIsDelete(productToOrder.getProductId(),"NO");
            ordersDetailRepo.save(OrdersDetailMapper.toOrderDetailEntity(productToOrder,orderCreate,product));
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
    public void sendEmailOrder() {

    }

    @Override
    public List<OrderDetailView> getOrderDetailByCustomerIdAndStatus(int id,OrdersStatusRequest ordersStatusRequest) {
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(id,"NO");
        List<OrdersEntity> listOrder=orderRepo.findOrdersEntityByCustomerEntityAndStatusOrder(customer,ordersStatusRequest.getStatusOrder());
        List<OrderDetailView> orderDetailViewList=new ArrayList<OrderDetailView>();
        for(OrdersEntity orders:listOrder)
        {
            List<OrderDetailEntity> listOrderDetail=ordersDetailRepo.findOrderDetailEntityByOrdersEntity(orders);
            for(OrderDetailEntity orderDetailEntity:listOrderDetail)
            {
                OrderDetailView orderDetailView=new OrderDetailView();
                orderDetailView.setOrderId(orderDetailEntity.getId().getOrderId());
                orderDetailView.setProducId(orderDetailEntity.getId().getProductId());
                orderDetailView.setProductName(orderDetailEntity.getProductEntity().getProductName());
                orderDetailView.setProductImage(orderDetailEntity.getProductEntity().getProductImage());
                orderDetailView.setQuantity(orderDetailEntity.getQuantity());
                orderDetailView.setAmount(orderDetailEntity.getAmount());
                orderDetailViewList.add(orderDetailView);
            }
        }

        return orderDetailViewList;
    }

}
