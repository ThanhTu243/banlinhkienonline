package com.thanhtu.crud.model.dto;

import lombok.Data;

@Data
public class DeliveryAddressDto {
    private Integer deliveryAddressId;
    private String deliveryAddress;
    private String fullname;
    private String phoneNumber;
}
