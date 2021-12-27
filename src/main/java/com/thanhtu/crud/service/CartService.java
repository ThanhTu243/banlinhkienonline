package com.thanhtu.crud.service;

import com.thanhtu.crud.model.dto.CartByCustomerDto;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.request.CartRequest;

public interface CartService{
    CartDto createCart(CartRequest cartRequest);

    CartDto updateCart(CartRequest cartRequest);

    CartDto deleteCart(CartRequest cartRequest);

    CartByCustomerDto getCartByCustomer(int customerId);
}
