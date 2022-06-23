package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.model.dto.OrdersDetailDto;
import com.thanhtu.crud.model.dto.ProductToOrder;

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
    public static OrderDetailEntity toOrderDetailEntity(CartEntity cartEntity, OrdersEntity orders)
    {
        OrderDetailEntity tmp=new OrderDetailEntity();
        tmp.setId(OrderDetailIdKeyMapper.toOrderDetailIDKey(orders.getOrderId(),cartEntity.getProductEntity().getProductId()));
        tmp.setQuantity(cartEntity.getQuantity());
        Long priceAfterDiscount=cartEntity.getProductEntity().getUnitPrice().longValue()*(100-cartEntity.getProductEntity().getDiscount())/100;
        Long amountProduct=cartEntity.getQuantity().longValue()*priceAfterDiscount;
        tmp.setAmount(amountProduct);
        tmp.setIsDelete("NO");
        tmp.setOrdersEntity(orders);
        tmp.setIsReview("NO");
        tmp.setProductEntity(cartEntity.getProductEntity());
        return tmp;
    }
}
