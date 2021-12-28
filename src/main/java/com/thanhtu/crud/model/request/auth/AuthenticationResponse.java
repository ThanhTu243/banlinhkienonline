package com.thanhtu.crud.model.request.auth;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private Integer id;
    private String username;
    private String token;
    private String userRole;
}
