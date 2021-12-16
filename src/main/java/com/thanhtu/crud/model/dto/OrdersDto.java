package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.model.dto.fk.CustomerFKDto;
import lombok.Data;

@Data
public class OrdersDto {
    private Integer orderId;
    private String address;
    private String phoneNumber;
    private String createDate;
    private Integer totalAmount;
    private String statusOrder;
    private CustomerFKDto customerFKDto;
}
