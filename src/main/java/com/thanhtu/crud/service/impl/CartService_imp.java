package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.CartEntity;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.mapper.CartMapper;
import com.thanhtu.crud.model.request.CartRequest;
import com.thanhtu.crud.repository.CartRepository;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.repository.ProductRepository;
import com.thanhtu.crud.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService_imp implements CartService {
    @Autowired
    CartRepository cartRepo;
    @Autowired
    ProductRepository proRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Override
    public CartDto createCart(CartRequest cartRequest) {
        ProductEntity product=proRepo.getById(cartRequest.getProductId());
        CustomerEntity customer=customerRepo.getById(cartRequest.getCustomerId());
        CartEntity cartEntity=cartRepo.save(CartMapper.toCartEntity(cartRequest,product,customer));
        return null;
    }
}
