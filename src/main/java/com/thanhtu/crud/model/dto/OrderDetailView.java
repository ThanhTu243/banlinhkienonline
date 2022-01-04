package com.thanhtu.crud.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDetailView {
    private Integer orderId;
    private String createDate;
    private List<ProductOrder> listProduct;
    private Long amount;
}
