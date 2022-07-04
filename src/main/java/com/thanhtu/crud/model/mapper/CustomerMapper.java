package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.*;
import com.thanhtu.crud.model.dto.fk.CustomerFKDto;
import com.thanhtu.crud.model.request.CustomerRequest;
import com.thanhtu.crud.model.request.ProfileRequest;

import java.util.ArrayList;
import java.util.List;


public class CustomerMapper {
    public static CustomerDto toCustomerDto(CustomerEntity customerEntity)
    {
        CustomerDto tmp = new CustomerDto();
        tmp.setUserCustomer(customerEntity.getUserCustomer());
        tmp.setAddress(customerEntity.getAddress());
        tmp.setCustomerId(customerEntity.getCustomerId());
        tmp.setFirstName(customerEntity.getFirstnameCustomer());
        tmp.setLastName(customerEntity.getLastnameCustomer());
        tmp.setGmailCustomer(customerEntity.getGmailCustomer());
        tmp.setPhoneNumberCustomer(customerEntity.getPhonenumberCustomer());
        tmp.setImage(customerEntity.getImageCustomer());
        return tmp;
    }

    public static ProfileDto toProfileDto(CustomerEntity customerEntity,String provider)
    {
        ProfileDto tmp = new ProfileDto();
        tmp.setUserCustomer(customerEntity.getUserCustomer());
        tmp.setAddress(customerEntity.getAddress());
        tmp.setCustomerId(customerEntity.getCustomerId());
        tmp.setFirstName(customerEntity.getFirstnameCustomer());
        tmp.setLastName(customerEntity.getLastnameCustomer());
        tmp.setGmailCustomer(customerEntity.getGmailCustomer());
        tmp.setPhoneNumberCustomer(customerEntity.getPhonenumberCustomer());
        tmp.setImage(customerEntity.getImageCustomer());
        tmp.setProvider(provider);
        return tmp;
    }
    public static CustomerFKDto toCustomerFKDto(CustomerEntity customerEntity)
    {

        CustomerFKDto tmp=new CustomerFKDto();
        tmp.setCustomerId(customerEntity.getCustomerId());
        tmp.setUserCustomer(customerEntity.getUserCustomer());
        tmp.setFirstName(customerEntity.getFirstnameCustomer());
        tmp.setLastName(customerEntity.getLastnameCustomer());
        tmp.setGmailCustomer(customerEntity.getGmailCustomer());
        tmp.setPhoneNumberCustomer(customerEntity.getPhonenumberCustomer());
        tmp.setImage(customerEntity.getImageCustomer());
        tmp.setAddress(customerEntity.getAddress());
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
        customer.setFirstnameCustomer(request.getFirstname());
        customer.setLastnameCustomer(request.getLastname());
        customer.setAddress(request.getAddress());
        return customer;
    }
    public static CustomerEntity toUpdateCustomerEntity(CustomerEntity customer, ProfileRequest request)
    {
        customer.setPhonenumberCustomer(request.getPhoneNumber());
        customer.setFirstnameCustomer(request.getFirstName());
        customer.setLastnameCustomer(request.getLastName());
        customer.setAddress(request.getAddress());
        customer.setImageCustomer(request.getImage());
        customer.setGmailCustomer(request.getEmail());
        return customer;
    }

}
