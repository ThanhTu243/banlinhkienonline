package com.thanhtu.crud.model.dto.fk;

import lombok.Data;

@Data
public class OrdersFKDto {
    private Integer orderId;
    private String address;
    private String phoneNumber;
    private String createDate;
    private Integer totalAmount;
    private String statusOrder;
}
