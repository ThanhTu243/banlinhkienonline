package com.thanhtu.crud.model.request.auth;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private Integer Id;
    private Integer customerId;
    private String username;
    private String token;
    private String userRole;
    private String imageLink;
}
