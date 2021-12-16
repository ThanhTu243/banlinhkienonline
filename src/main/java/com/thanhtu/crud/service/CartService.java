package com.thanhtu.crud.service;

import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.request.CartRequest;

public interface CartService{
    CartDto createCart(CartRequest cartRequest);
}
