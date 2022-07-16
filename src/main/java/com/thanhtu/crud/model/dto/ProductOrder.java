package com.thanhtu.crud.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductOrder {
    private Integer orderId;
    private Integer producId;
    private String productName;
    private String productImage;
    private Integer quantity;
}
