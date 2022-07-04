package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.dto.ProfileDto;
import com.thanhtu.crud.model.request.CartRequest;
import com.thanhtu.crud.model.request.CustomerRequest;
import com.thanhtu.crud.model.request.ProfileRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerEntity getCustomerById(int id);
    ProfileDto getCustomerById(int customerId, int accountId);

    Page<CustomerEntity> getListCustomer(Pageable pageable);

    void deleteCustomer(Integer id);

    CustomerEntity updateProfile(ProfileRequest profileRequest);

    CustomerDto updateCustomer(int customerId,CustomerRequest customerRequest);

}
