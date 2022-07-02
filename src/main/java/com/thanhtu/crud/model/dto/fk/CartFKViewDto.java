package com.thanhtu.crud.model.dto.fk;

import com.thanhtu.crud.model.dto.ProductImageDto;
import lombok.Data;

import java.util.List;

@Data
public class CartFKViewDto {
    private Integer cartId;
    private Integer productId;
    private String nameProduct;
    private List<ProductImageDto> productImageDtoSet;
    private Integer quantity;
    private Integer discount;
    private Integer unitPrice;
    private Long priceAfterDiscount;
    private Long cost;
}
