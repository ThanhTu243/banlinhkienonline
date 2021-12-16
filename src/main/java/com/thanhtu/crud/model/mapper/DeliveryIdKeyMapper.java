package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.DeliveryIDKey;
import com.thanhtu.crud.model.request.OrdersAssignRequest;

public class DeliveryIdKeyMapper {
    public static DeliveryIDKey toDeliveryIdKey(OrdersAssignRequest ordersAssignRequest)
    {
        DeliveryIDKey tmp=new DeliveryIDKey();
        tmp.setOrder_id(ordersAssignRequest.getOrderId());
        tmp.setShipper_id(ordersAssignRequest.getShipperId());
        return tmp;
    }
}
