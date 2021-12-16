package com.thanhtu.crud.model.dto.fk;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFKDto {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private String productImage;
    private Integer discount;
    private Integer unitPrice;
    private String descriptionProduct;
}
