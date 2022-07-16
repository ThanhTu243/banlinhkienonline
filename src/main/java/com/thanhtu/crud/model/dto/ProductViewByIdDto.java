package com.thanhtu.crud.model.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductViewByIdDto {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private String isDelete;
    private List<ProductImageDto> productImageList;
    private Integer discount;
    private Integer unitPrice;
    private Long priceAfterDiscount;
    private String descriptionProduct;
    private String rating;
    private List<ReviewsDto> listReviews;
}
