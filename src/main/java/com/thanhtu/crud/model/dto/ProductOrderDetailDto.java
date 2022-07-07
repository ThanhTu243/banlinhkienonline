package com.thanhtu.crud.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductOrderDetailDto {
   private Integer productId;
   private String nameProduct;
   private List<ProductImageDto> productImageSet;
   private Long priceAfterDiscount;
   private Integer quantity;
   private Long amount;
}
