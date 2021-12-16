package com.thanhtu.crud.model.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ShipperRequest {
    @NotNull(message = "Nhập tên đăng nhập của shipper")
    @NotEmpty(message = "Nhập tên đăng nhập của shipper")
    private String userShipper;

    @NotNull(message = "Nhập tên mật khẩu của shipper")
    @NotEmpty(message = "Nhập tên mật khẩu của shipper")
    @Size(min = 4,max = 20,message = "Nhập mật khẩu từ 4 đến 20 ký tự")
    @Pattern(regexp = "[^ ]+",message = "Không nhập dấu cách")
    private String passwordShipper;

    @NotNull(message = "Nhập tên shipper")
    @NotEmpty(message = "Nhập tên shipper")
    @Size(max =100,message = "Nhập tên shipper nhỏ hơn 10 ký tự")
    private String fullnameShipper;

    @NotNull(message = "Nhập mật khẩu quản trị viên")
    @NotEmpty(message = "Nhập mật khẩu quản trị viên")
    @Email(message = "Vui lòng nhập đúng định dạng Email")
    private String gmailShipper;
}
