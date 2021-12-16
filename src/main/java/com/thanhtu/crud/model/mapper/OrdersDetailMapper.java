package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.OrdersDetailDto;

public class OrdersDetailMapper {
    public static OrdersDetailDto toOrdersDetailDto(OrderDetailEntity orderDetailEntity, ProductEntity productEntity)
    {
        OrdersDetailDto tmp =new OrdersDetailDto();
        tmp.setOrderId(orderDetailEntity.getId().getOrder_id());
        tmp.setProductId(orderDetailEntity.getId().getProduct_id());
        tmp.setQuantity(orderDetailEntity.getQuantity());
        tmp.setAmount(orderDetailEntity.getAmount());
        tmp.setProductFKDto(ProductMapper.toProductFKDto(productEntity));
        return tmp;
    }
}
