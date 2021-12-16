package com.thanhtu.crud.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Setter
@Getter
public class LoginRequest {
    @NotNull(message = "Nhập tên đăng nhập của quản trị viên")
    @NotEmpty(message = "Nhập tên đăng nhập của quản trị viên")
    private String userAdmin;

    @NotNull(message = "Nhập tên mật khẩu của quản trị viên")
    @NotEmpty(message = "Nhập tên mật khẩu của quản trị viên")
    @Size(min = 4,max = 20,message = "Nhập mật khẩu từ 4 đến 20 ký tự")
    @Pattern(regexp = "[^ ]+",message = "Không nhập dấu cách")
    private String passwordAdmin;
}
