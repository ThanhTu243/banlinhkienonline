package com.thanhtu.crud.model.dto;

import lombok.Data;

@Data
public class OrderDetailView {
    private Integer orderId;
    private Integer producId;
    private String productName;
    private String productImage;
    private Integer quantity;
    private Integer amount;
}
