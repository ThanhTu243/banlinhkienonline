package com.thanhtu.crud.model.dto;

import lombok.Data;

@Data
public class AccountsDto {
    private Integer id;
    private String username;
    private String passwords;
    private String gmail;
    private String activationCode;
    private String passwordreset_code;
    private String activeAccount;
    private String provider;
    private String roles;
}
