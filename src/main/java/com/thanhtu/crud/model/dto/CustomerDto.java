package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.entity.CartEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Integer customerId;
    private String userCustomer;
    private String passwordCustomer;
    private String fullnameCustomer;
    private String gmailCustomer;
    private String phoneNumberCustomer;
    private Set<CartEntity> cartEntities;
    private Set<OrdersEntity> ordersEntities;
}
