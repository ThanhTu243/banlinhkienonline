package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.OrderDetailIDKey;

public class OrderDetailIdKeyMapper {
    public static OrderDetailIDKey toOrderDetailIDKey(int orderId,int productId)
    {
        OrderDetailIDKey tmp=new OrderDetailIDKey();
        tmp.setOrderId(orderId);
        tmp.setProductId(productId);
        return tmp;
    }
}
