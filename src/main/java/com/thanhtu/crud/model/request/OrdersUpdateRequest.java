package com.thanhtu.crud.model.request;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.model.dto.fk.CustomerFKDto;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class OrdersUpdateRequest {
    @NotBlank(message = "Thiếu địa chỉ")
    private String address;
    @NotBlank(message = "Thiếu số điện thoại")
    @Length(min = 10,max = 10,message = "Số điện thoại phải gồm 10 số")
    private String phoneNumber;
}
