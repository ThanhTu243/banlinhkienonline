package com.thanhtu.crud.model.dto;

import lombok.Data;

@Data
public class PaypalDto {
    private Integer customerId;
    private Integer orderId;
    private String link;
}
