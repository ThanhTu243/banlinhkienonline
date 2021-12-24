package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.DeliveryIDKey;
import com.thanhtu.crud.model.request.OrdersAssignRequest;

public class DeliveryIdKeyMapper {
    public static DeliveryIDKey toDeliveryIdKey(OrdersAssignRequest ordersAssignRequest)
    {
        DeliveryIDKey tmp=new DeliveryIDKey();
        tmp.setOrderId(ordersAssignRequest.getOrderId());
        tmp.setShipperId(ordersAssignRequest.getShipperId());
        return tmp;
    }
}
