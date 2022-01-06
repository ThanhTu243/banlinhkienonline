package com.thanhtu.crud.model.dto;

import lombok.Data;

@Data
public class ProductOrderDetailDto {
   private Integer productId;
   private String nameProduct;
   private String productImage;
   private Long priceAfterDiscount;
   private Integer quantity;
   private Long amount;
}
