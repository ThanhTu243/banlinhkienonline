package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.dto.fk.OrdersFKDto;
import com.thanhtu.crud.model.request.OrdersUpdateRequest;

public class OrdersMapper {
    public static OrdersDto toOrdersDto(OrdersEntity ordersEntity)
    {
        OrdersDto tmp=new OrdersDto();
        tmp.setOrderId(ordersEntity.getOrderId());
        tmp.setPhoneNumber(ordersEntity.getPhoneNumber());
        tmp.setTotalAmount(ordersEntity.getTotalAmount());
        tmp.setAddress(ordersEntity.getAddress());
        tmp.setCreateDate(ordersEntity.getCreateDate().toString());
        tmp.setStatusOrder(ordersEntity.getStatusOrder());
        tmp.setCustomerFKDto(CustomerMapper.toCustomerFKDto(ordersEntity.getCustomerEntity()));
        return tmp;
    }
    public static OrdersEntity toUpdateOrders(OrdersEntity ordersEntity, OrdersUpdateRequest ordersUpdateRequest)
    {
        ordersEntity.setAddress(ordersUpdateRequest.getAddress());
        ordersEntity.setPhoneNumber(ordersUpdateRequest.getPhoneNumber());
        return ordersEntity;
    }
    public static OrdersFKDto toOrdersFKDto(OrdersEntity ordersEntity)
    {
        OrdersFKDto tmp=new OrdersFKDto();
        tmp.setOrderId(ordersEntity.getOrderId());
        tmp.setAddress(ordersEntity.getAddress());
        tmp.setPhoneNumber(ordersEntity.getPhoneNumber());
        tmp.setCreateDate(ordersEntity.getCreateDate().toString());
        tmp.setTotalAmount(ordersEntity.getTotalAmount());
        tmp.setStatusOrder(ordersEntity.getStatusOrder());
        return tmp;
    }
}
