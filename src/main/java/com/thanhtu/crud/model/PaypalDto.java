package com.thanhtu.crud.model;

import lombok.Data;

@Data
public class PaypalDto {
    private Integer customerId;
    private Integer orderId;
    private String link;
}
