package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.request.CartRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerEntity getCustomerById(int id);

    Page<CustomerEntity> getListCustomer(Pageable pageable);

    void deleteCustomer(Integer id);
}
