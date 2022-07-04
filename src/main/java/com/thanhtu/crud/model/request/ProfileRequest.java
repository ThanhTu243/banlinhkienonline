package com.thanhtu.crud.model.request;

import lombok.Data;

@Data
public class ProfileRequest {
    private Integer id;
    private Integer customerId;
    private String username;
    private String firstName;
    private String lastName;
    private String image;
    private String email;
    private String phoneNumber;
    private String address;
}
