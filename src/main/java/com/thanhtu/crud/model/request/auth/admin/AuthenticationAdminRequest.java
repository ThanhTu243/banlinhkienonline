package com.thanhtu.crud.model.request.auth.admin;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationAdminRequest {
    @NotNull(message = "Vui lòng nhập tên tài khoản")
    @NotEmpty(message = "Vui lòng nhập tên tài khoản")
    private String userAdmin;

    @NotNull(message = "Vui lòng nhập mật khẩu")
    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    private String password;
}
