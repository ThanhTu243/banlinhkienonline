package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CartIDKey;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.CartIDKeyDto;
import com.thanhtu.crud.model.dto.pk.CartIDPKDto;
import com.thanhtu.crud.model.request.CartRequest;

public class CartIdKeyMapper {
    public static CartIDKey toCartIdKey(CustomerEntity customer, ProductEntity product)
    {
        CartIDKey tmp=new CartIDKey();
        tmp.setCustomerId(customer.getCustomerId());
        tmp.setProductId(product.getProductId());
        return tmp;
    }
    public static CartIDKey toCartIdKey(CartRequest cartRequest)
    {
        CartIDKey tmp=new CartIDKey();
        tmp.setCustomerId(cartRequest.getCustomerId());
        tmp.setProductId(cartRequest.getProductId());
        return tmp;
    }
    public static CartIDKeyDto toCartIdKeyDto(CartIDKey cartIDKey)
    {
        CartIDKeyDto tmp=new CartIDKeyDto();
        tmp.setCustomerId(cartIDKey.getCustomerId());
        tmp.setProductId(cartIDKey.getProductId());
        return tmp;
    }
    public static CartIDPKDto toCartIDPKDto(CustomerEntity customerEntity,ProductEntity productEntity)
    {
        CartIDPKDto tmp=new CartIDPKDto();
        tmp.setCustomerId(customerEntity.getCustomerId());
        tmp.setProductId(productEntity.getProductId());
        return tmp;
    }
}
