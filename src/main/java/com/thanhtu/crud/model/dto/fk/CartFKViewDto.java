package com.thanhtu.crud.model.dto.fk;

import lombok.Data;

@Data
public class CartFKViewDto {
    private Integer cartId;
    private Integer productId;
    private String nameProduct;
    private String productImage;
    private Integer quantity;
    private Integer discount;
    private Integer unitPrice;
    private Long priceAfterDiscount;
    private Long cost;
}
