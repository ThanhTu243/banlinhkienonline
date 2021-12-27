package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.mapper.CategoryMapper;
import com.thanhtu.crud.model.mapper.CustomerMapper;
import com.thanhtu.crud.model.mapper.ProductMapper;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.repository.ProductRepository;
import com.thanhtu.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService_impl implements CustomerService {
    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    AccountsRepository accountsRepo;
    @Override
    public CustomerEntity getCustomerById(int id) {
        CustomerEntity customer=customerRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại khách hàng với id: "+id));
        return customer;
    }

    @Override
    public Page<CustomerEntity> getListCustomer(Pageable pageable) {
        return customerRepo.findCustomerEntityByIsDelete("NO",pageable);
    }

    @Override
    public void deleteCustomer(Integer id) {
        CustomerEntity customerEntity=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(id,"NO");
        if(customerEntity==null)
        {
            throw new NotFoundException("Không tài tại tài khoản với id: "+id);
        }
        AccountsEntity account=accountsRepo.findAccountsEntitiesByUsername(customerEntity.getUserCustomer());
        customerEntity.setIsDelete("YES");
        account.setActiveAccount("NOT ACTIVE");
        customerRepo.save(customerEntity);
        accountsRepo.save(account);
    }

}
