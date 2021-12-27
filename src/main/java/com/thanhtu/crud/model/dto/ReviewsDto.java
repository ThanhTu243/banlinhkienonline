package com.thanhtu.crud.model.dto;

import lombok.Data;

@Data
public class ReviewsDto {
    private Integer orderId;
    private String productName;
    private String customerName;
    private String comments;
    private Integer rating;
}
