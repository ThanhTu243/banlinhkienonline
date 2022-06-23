package com.thanhtu.crud.service;

import com.thanhtu.crud.model.dto.CartByCustomerDto;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.request.CartRequest;
import com.thanhtu.crud.model.request.CartSelectRequest;

public interface CartService{
    CartDto createCart(CartRequest cartRequest);

    CartDto updateCart(CartRequest cartRequest);

    CartDto deleteCart(CartRequest cartRequest);

    int deleteCartById(int id);

    CartByCustomerDto getCartByCustomer(int customerId);

    Long selectCart(CartSelectRequest cartSelectRequest);
}
