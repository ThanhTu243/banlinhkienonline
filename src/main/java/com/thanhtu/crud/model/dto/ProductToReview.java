package com.thanhtu.crud.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductToReview {
    private Integer orderId;
    private Integer productId;
    private String productName;
    private String productImage;
    private Integer quantity;
    private String createOrders;
    private String isReview;
}
