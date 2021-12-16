package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.request.CartRequest;

public class CartMapper {
    public static CartEntity toCartEntity(CartRequest cartRequest, ProductEntity productEntity, CustomerEntity customerEntity)
    {
        CartEntity tmp=new CartEntity();
        tmp.setId(CartIdKeyMapper.toCartIdKey(customerEntity,productEntity));
        tmp.setQuantity(cartRequest.getQuantity());
        tmp.setCustomerEntity(customerEntity);
        tmp.setProductEntity(productEntity);
        return tmp;
    }
}
