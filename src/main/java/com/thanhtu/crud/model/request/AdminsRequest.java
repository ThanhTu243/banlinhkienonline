package com.thanhtu.crud.model.request;

import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminsRequest {
    @NotNull(message = "Nhập tên đăng nhập của quản trị viên")
    @NotEmpty(message = "Nhập tên đăng nhập của quản trị viên")
    private String userAdmin;

    @NotNull(message = "Nhập tên mật khẩu của quản trị viên")
    @NotEmpty(message = "Nhập tên mật khẩu của quản trị viên")
    @Size(min = 4,max = 20,message = "Nhập mật khẩu từ 4 đến 20 ký tự")
    @Pattern(regexp = "[^ ]+",message = "Không nhập dấu cách")
    private String passwordAdmin;

    @NotNull(message = "Nhập tên quản trị viên")
    @NotEmpty(message = "Nhập tên quản trị viên")
    @Size(max =100,message = "Nhập tên quản trị viên nhỏ hơn 100 ký tự")
    private String fullnameAdmin;

    @NotNull(message = "Nhập mật khẩu quản trị viên")
    @NotEmpty(message = "Nhập mật khẩu quản trị viên")
    @Email(message = "Vui lòng nhập đúng định dạng Email")
    private String gmailAdmin;
}
