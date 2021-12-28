package com.thanhtu.crud.model.request;

import lombok.Data;

@Data
public class ProductSelectCartRequest {
    private Integer productId;
    private Integer quantity;
}
