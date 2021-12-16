package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CartIDKey;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.CartIDKeyDto;

public class CartIdKeyMapper {
    public static CartIDKey toCartIdKey(CustomerEntity customer, ProductEntity product)
    {
        CartIDKey tmp=new CartIDKey();
        tmp.setCustomer_id(customer.getCustomer_id());
        tmp.setProduct_id(product.getProduct_id());
        return tmp;
    }
    public static CartIDKeyDto toCartIdKeyDto(CartIDKey cartIDKey)
    {
        CartIDKeyDto tmp=new CartIDKeyDto();
        tmp.setCustomerId(cartIDKey.getCustomer_id());
        tmp.setProductId(cartIDKey.getProduct_id());
        return tmp;
    }
}
