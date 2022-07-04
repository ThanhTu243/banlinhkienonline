package com.thanhtu.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Integer customerId;
    private String userCustomer;
    private String firstName;
    private String lastName;
    private String address;
    private String gmailCustomer;
    private String phoneNumberCustomer;
    private String image;
    private String provider;

}
