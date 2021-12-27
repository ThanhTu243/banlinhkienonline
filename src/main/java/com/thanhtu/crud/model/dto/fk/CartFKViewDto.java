package com.thanhtu.crud.model.dto.fk;

import lombok.Data;

@Data
public class CartFKViewDto {
    private String nameProduct;
    private Integer quantity;
    private Integer unitPrice;
    private Integer cost;
}
