package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.DeliveryEntity;
import com.thanhtu.crud.entity.DeliveryIDKey;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ShipperEntity;
import com.thanhtu.crud.model.request.OrdersAssignRequest;

public class DeliveryMapper {
    public static DeliveryEntity toDeliveryEntity(OrdersAssignRequest ordersAssignRequest, ShipperEntity shipperEntity, OrdersEntity ordersEntity)
    {
        DeliveryEntity tmp =new DeliveryEntity();
        tmp.setId(DeliveryIdKeyMapper.toDeliveryIdKey(ordersAssignRequest));
        tmp.setShipperEntity(shipperEntity);
        tmp.setOrdersEntity(ordersEntity);
        return tmp;
    }
}
