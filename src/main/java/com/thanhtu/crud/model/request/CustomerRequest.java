package com.thanhtu.crud.model.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {

    @NotNull(message = "Nhập họ khách hàng")
    @NotEmpty(message = "Nhập họ khách hàng")
    @Size(max =100,message = "Nhập họ khách hàng nhỏ hơn 100 ký tự")
    private String firstname;

    @NotNull(message = "Nhập tên khách hàng")
    @NotEmpty(message = "Nhập tên khách hàng")
    @Size(max =100,message = "Nhập tên khách hàng nhỏ hơn 100 ký tự")
    private String lastname;

    @NotNull(message = "Nhập địa chỉ khách hàng")
    @NotEmpty(message = "Nhập địa chỉ khách hàng")
    private String address;

    @NotNull(message = "Nhập số điện thoại khách hàng")
    @NotEmpty(message = "Nhập số điện thoại khách hàng")
    @Length(min = 10,max = 10,message = "Nhập đủ 10 số điện thoại")
    private String phoneNumber;
}
