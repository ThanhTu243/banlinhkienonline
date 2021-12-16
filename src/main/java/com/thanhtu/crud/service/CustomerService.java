package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.request.CartRequest;

public interface CustomerService {
    CustomerEntity getCustomerById(int id);
}
