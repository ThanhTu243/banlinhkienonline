package com.thanhtu.crud.model.request;

import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {
    @NotNull(message = "Nhập tên khách hàng")
    @NotEmpty(message = "Nhập tên khách hàng")
    private String userCustomer;

    @NotNull(message = "Nhập mật khẩu của khách hàng")
    @NotEmpty(message = "Nhập mật khẩu của khách hàng")
    @Size(min = 4,max = 20,message = "Nhập mật khẩu từ 4 đến 20 ký tự")
    @Pattern(regexp = "[^ ]+",message = "Không nhập dấu cách")
    private String passwordCustomer;

    @NotNull(message = "Nhập tên khách hàng")
    @NotEmpty(message = "Nhập tên khách hàng")
    @Size(max =100,message = "Nhập tên khách hàng nhỏ hơn 10 ký tự")
    private String fullnameCustomer;

    @NotNull(message = "Nhập mật khẩu khách hàng")
    @NotEmpty(message = "Nhập mật khẩu khách hàng")
    @Email(message = "Vui lòng nhập đúng định dạng Email")
    private String gmailAdmin;
}
