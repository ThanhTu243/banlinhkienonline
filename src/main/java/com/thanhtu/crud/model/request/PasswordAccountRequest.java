package com.thanhtu.crud.model.request;

import lombok.Data;

@Data
public class PasswordAccountRequest {
    private Integer id;
    private Integer customerId;
    private String currentPassword;
    private String newPassword;
    private String reNewPassword;
}
