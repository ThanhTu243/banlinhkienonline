package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.model.dto.fk.CartFKViewDto;
import lombok.Data;

import java.util.Set;

@Data
public class CartByCustomerDto {
    private Integer customerId;
    private String userCustomer;
    private String fullnameCustomer;
    private String address;
    private String gmailCustomer;
    private String phoneNumberCustomer;
    private Set<CartFKViewDto> cartEntities;
}
