package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.ShipperEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.mapper.DeliveryMapper;
import com.thanhtu.crud.model.mapper.OrdersMapper;
import com.thanhtu.crud.model.request.*;
import com.thanhtu.crud.repository.*;
import com.thanhtu.crud.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class OrdersService_impl implements OrdersService {
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


    @Override
    public List<OrdersEntity> getListOrderByStatus(OrdersStatusRequest ordersStatusRequest) {
        List<OrdersEntity> list=orderRepo.findOrdersEntityByStatusOrder(ordersStatusRequest.getStatusOrder());
        return list;
    }

    @Override
    public OrdersDto updateOrders(int id, OrdersUpdateRequest ordersUpdateRequest) {
        OrdersEntity ordersEntity=orderRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại đơn hàng với id: "+id));
        OrdersEntity orders=orderRepo.save(OrdersMapper.toUpdateOrders(ordersEntity,ordersUpdateRequest));
        return OrdersMapper.toOrdersDto(orders);
    }

    @Override
    public void approvalOrders(List<OrdersUpdateStatusRequest> listId) {
        for(OrdersUpdateStatusRequest orderId:listId)
        {
            OrdersEntity ordersEntity=orderRepo.findById(orderId.getId()).orElseThrow(()-> new NotFoundException("Không tồn tại đơn hàng với id: "+orderId.getId()));
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
}
