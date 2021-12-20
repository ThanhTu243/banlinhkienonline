package com.thanhtu.crud.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class PasswordResetRequest {
    @Email(message = "Nhập email đúng định dạng")
    private String email;

    @Size(min = 6,max = 24, message = "Mật khẩu từ 6 đến 24 ký tự")
    private String password;

    @Size(min = 6,max = 24, message = "Mật khẩu từ 6 đến 24 ký tự")
    private String repassword;
}
