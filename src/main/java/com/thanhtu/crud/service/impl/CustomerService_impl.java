package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.dto.ProfileDto;
import com.thanhtu.crud.model.mapper.CustomerMapper;
import com.thanhtu.crud.model.request.CustomerRequest;
import com.thanhtu.crud.model.request.ProfileRequest;
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
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(id,"NO");
        if(customer==null)
        {
            throw new NotFoundException("Không tồn tại khách hàng với id: "+id);
        }

        return customer;
    }

    @Override
    public ProfileDto getCustomerById(int customerId, int accountId) {
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(customerId,"NO");
        if(customer==null)
        {
            throw new NotFoundException("Không tồn tại khách hàng với id: "+customerId);
        }
        AccountsEntity accounts=accountsRepo.findAccountsEntitiesByAccountId(accountId);
        return CustomerMapper.toProfileDto(customer,accounts.getProvider());
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

    @Override
    public CustomerEntity updateProfile(ProfileRequest profileRequest) {
        CustomerEntity customerEntity=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(profileRequest.getCustomerId(),"NO");
        if(customerEntity==null)
        {
            throw new NotFoundException("Không tồn tại tài khoản với id: "+profileRequest.getCustomerId());
        }
        return customerRepo.save(CustomerMapper.toUpdateCustomerEntity(customerEntity,profileRequest));
    }

    @Override
    public CustomerDto updateCustomer(int customerId, CustomerRequest customerRequest) {
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(customerId,"NO");
        if(customer==null)
        {
            throw new NotFoundException("Không tồn tại khách hàng với id: "+customerId);
        }
        CustomerEntity customerUpdate=customerRepo.save(CustomerMapper.toUpdateCustomerEntity(customer,customerRequest));
        return CustomerMapper.toCustomerDto(customerUpdate);
    }

}
