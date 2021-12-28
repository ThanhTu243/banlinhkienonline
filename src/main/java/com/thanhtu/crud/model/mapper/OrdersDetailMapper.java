package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.model.dto.OrderDetailViewDto;
import com.thanhtu.crud.model.dto.OrdersDetailDto;
import com.thanhtu.crud.model.dto.ProductToOrder;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.List;

import java.util.ArrayList;
import java.util.Set;

public class OrdersDetailMapper {
    public static OrdersDetailDto toOrdersDetailDto(OrderDetailEntity orderDetailEntity, ProductEntity productEntity)
    {
        OrdersDetailDto tmp =new OrdersDetailDto();
        tmp.setOrderId(orderDetailEntity.getId().getOrderId());
        tmp.setProductId(orderDetailEntity.getId().getProductId());
        tmp.setQuantity(orderDetailEntity.getQuantity());
        tmp.setAmount(orderDetailEntity.getAmount());
        tmp.setProductFKDto(ProductMapper.toProductFKDto(productEntity));
        return tmp;
    }
    public static OrderDetailEntity toOrderDetailEntity(ProductToOrder productToOrder, OrdersEntity orders,ProductEntity product)
    {
        OrderDetailEntity tmp=new OrderDetailEntity();
        tmp.setId(OrderDetailIdKeyMapper.toOrderDetailIDKey(orders.getOrderId(), productToOrder.getProductId()));
        tmp.setQuantity(productToOrder.getQuantity());
        tmp.setAmount(Long.valueOf(productToOrder.getQuantity()*product.getUnitPrice()));
        tmp.setIsDelete("NO");
        tmp.setOrdersEntity(orders);
        tmp.setProductEntity(product);
        return tmp;
    }
}
