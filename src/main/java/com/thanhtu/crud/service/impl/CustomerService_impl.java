package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.mapper.CategoryMapper;
import com.thanhtu.crud.model.mapper.CustomerMapper;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService_impl implements CustomerService {
    @Autowired
    CustomerRepository customerRepo;
    @Override
    public CustomerEntity getCustomerById(int id) {
        CustomerEntity customer=customerRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại khách hàng với id: "+id));
//        return CustomerMapper.toCustomerDto(customer);
        return customer;
    }
}
