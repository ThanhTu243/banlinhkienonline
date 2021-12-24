package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CartIDKey;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.CartIDKeyDto;

public class CartIdKeyMapper {
    public static CartIDKey toCartIdKey(CustomerEntity customer, ProductEntity product)
    {
        CartIDKey tmp=new CartIDKey();
        tmp.setCustomerId(customer.getCustomerId());
        tmp.setProductId(product.getProductId());
        return tmp;
    }
    public static CartIDKeyDto toCartIdKeyDto(CartIDKey cartIDKey)
    {
        CartIDKeyDto tmp=new CartIDKeyDto();
        tmp.setCustomerId(cartIDKey.getCustomerId());
        tmp.setProductId(cartIDKey.getProductId());
        return tmp;
    }
}
