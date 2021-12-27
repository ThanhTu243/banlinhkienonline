package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.dto.CustomerPageDto;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.dto.ProductPageDto;
import com.thanhtu.crud.model.dto.fk.CustomerFKDto;
import com.thanhtu.crud.model.request.CustomerRequest;

import java.util.ArrayList;
import java.util.List;


public class CustomerMapper {
    public static CustomerDto toCustomerDto(CustomerEntity customerEntity)
    {
        CustomerDto tmp = new CustomerDto();
        tmp.setUserCustomer(customerEntity.getUserCustomer());
        tmp.setAddress(customerEntity.getAddress());
        tmp.setCustomerId(customerEntity.getCustomerId());
        tmp.setFullnameCustomer(customerEntity.getFullnameCustomer());
        tmp.setGmailCustomer(customerEntity.getGmailCustomer());
        tmp.setPhoneNumberCustomer(customerEntity.getPhonenumberCustomer());
        return tmp;
    }
    public static CustomerFKDto toCustomerFKDto(CustomerEntity customerEntity)
    {

        CustomerFKDto tmp=new CustomerFKDto();
        tmp.setCustomerId(customerEntity.getCustomerId());
        tmp.setUserCustomer(customerEntity.getUserCustomer());
        tmp.setFullnameCustomer(customerEntity.getFullnameCustomer());
        tmp.setGmailCustomer(customerEntity.getGmailCustomer());
        tmp.setPhoneNumberCustomer(customerEntity.getPhonenumberCustomer());
        return tmp;
    }
    public static CustomerPageDto toCustomerPageDto(List<CustomerEntity> customerList, int totalPage, int currentPage)
    {
        CustomerPageDto tmp=new CustomerPageDto();
        tmp.setCurrentPage(currentPage);
        tmp.setTotalPage(totalPage);
        List<CustomerDto> list=new ArrayList<CustomerDto>();
        for(CustomerEntity customerEn:customerList)
        {
            list.add(CustomerMapper.toCustomerDto(customerEn));
        }
        tmp.setListCustomer(list);
        return tmp;
    }
    public static CustomerEntity toUpdateCustomerEntity(CustomerEntity customer,CustomerRequest request)
    {
        customer.setPhonenumberCustomer(request.getPhoneNumber());
        customer.setFullnameCustomer(request.getFullname());
        customer.setAddress(request.getAddress());
        return customer;
    }

}
