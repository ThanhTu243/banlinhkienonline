package com.thanhtu.crud.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeliveryAddressRequest {
    @NotNull(message = "Vui lòng nhập địa customerId")
    private Integer customerId;
    @NotNull(message = "Vui lòng nhập địa chỉ giao hàng")
    private String deliveryAddress;
    @NotNull(message = "Vui lòng nhập tên người nhận")
    private String fullname;
    @NotNull(message = "Vui lòng nhập số điện thoại")
    private String phoneNumber;
}
