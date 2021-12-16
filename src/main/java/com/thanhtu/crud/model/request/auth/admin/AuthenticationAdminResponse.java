package com.thanhtu.crud.model.request.auth.admin;

import lombok.Data;

@Data
public class AuthenticationAdminResponse {
    private String userAdmin;
    private String token;
    private String role;
}
