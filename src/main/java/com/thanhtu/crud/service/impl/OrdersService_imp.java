package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ShipperEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.mapper.DeliveryMapper;
import com.thanhtu.crud.model.mapper.OrdersMapper;
import com.thanhtu.crud.model.request.*;
import com.thanhtu.crud.repository.DeliveryRepository;
import com.thanhtu.crud.repository.OrdersRepository;
import com.thanhtu.crud.repository.ShipperRepository;
import com.thanhtu.crud.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class OrdersService_imp implements OrdersService {
    @Autowired
    OrdersRepository orderRepo;
    @Autowired
    DeliveryRepository deliveryRepo;
    @Autowired
    ShipperRepository shipperRepo;


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
    public List<OrdersEntity> statics(RequestDate requestDate) {
        return orderRepo.findOrdersEntityByCreateDate(Timestamp.valueOf(requestDate.getFrom()));
    }
}
