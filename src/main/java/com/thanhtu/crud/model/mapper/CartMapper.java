package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.model.dto.CartByCustomerDto;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.dto.fk.CartFKViewDto;
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
    public static CartDto toCartDto(CartEntity cartEntity)
    {
        CartDto tmp=new CartDto();
        tmp.setId(CartIdKeyMapper.toCartIDPKDto(cartEntity.getCustomerEntity(), cartEntity.getProductEntity()));
        tmp.setQuantity(cartEntity.getQuantity());
        tmp.setCustomerFKDto(CustomerMapper.toCustomerFKDto(cartEntity.getCustomerEntity()));
        tmp.setProductFKDto(ProductMapper.toProductFKDto(cartEntity.getProductEntity()));
        return tmp;
    }
    public static CartFKViewDto toCartFKViewDto(ProductEntity product,CartEntity cart)
    {
        CartFKViewDto tmp=new CartFKViewDto();
        tmp.setProductId(product.getProductId());
        tmp.setNameProduct(product.getProductName());
        tmp.setUnitPrice(product.getUnitPrice());
        tmp.setDiscount(product.getDiscount());
        tmp.setPriceAfterDiscount((100-product.getDiscount())*product.getUnitPrice()/100);
        tmp.setQuantity(cart.getQuantity());
        tmp.setProductImage(product.getProductImage());

        tmp.setCost(Long.valueOf(tmp.getPriceAfterDiscount()* cart.getQuantity()));
        return tmp;
    }
}
