package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.dto.fk.CustomerFKDto;


public class CustomerMapper {
    public static CustomerDto toCustomerDto(CustomerEntity customerEntity)
    {
        CustomerDto tmp = new CustomerDto();
        tmp.setCustomerId(customerEntity.getCustomer_id());
        tmp.setPasswordCustomer(customerEntity.getPasswordCustomer());
        tmp.setFullnameCustomer(customerEntity.getFullnameCustomer());
        tmp.setGmailCustomer(customerEntity.getGmailCustomer());
        tmp.setPhoneNumberCustomer(customerEntity.getPhonenumberCustomer());
        tmp.setCartEntities(customerEntity.getCartEntities());
        return tmp;
    }
    public static CustomerFKDto toCustomerFKDto(CustomerEntity customerEntity)
    {

        CustomerFKDto tmp=new CustomerFKDto();
        tmp.setCustomerId(customerEntity.getCustomer_id());
        tmp.setPasswordCustomer(customerEntity.getPasswordCustomer());
        tmp.setFullnameCustomer(customerEntity.getFullnameCustomer());
        tmp.setGmailCustomer(customerEntity.getGmailCustomer());
        tmp.setPhoneNumberCustomer(customerEntity.getPhonenumberCustomer());
        return tmp;
    }

}
