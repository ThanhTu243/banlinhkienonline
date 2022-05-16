package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.CartIDKeyDto;
import com.thanhtu.crud.model.dto.pk.CartIDPKDto;
import com.thanhtu.crud.model.request.CartRequest;

public class CartIdKeyMapper {
    /*public static CartIDKeyDto toCartIdKeyDto(CartIDKey cartIDKey)
    {
        CartIDKeyDto tmp=new CartIDKeyDto();
        tmp.setCustomerId(cartIDKey.getCustomerId());
        tmp.setProductId(cartIDKey.getProductId());
        return tmp;
    }*/
    public static CartIDPKDto toCartIDPKDto(CustomerEntity customerEntity,ProductEntity productEntity)
    {
        CartIDPKDto tmp=new CartIDPKDto();
        tmp.setCustomerId(customerEntity.getCustomerId());
        tmp.setProductId(productEntity.getProductId());
        return tmp;
    }
}
