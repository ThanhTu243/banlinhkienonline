package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.model.dto.fk.CategoryFKDto;
import com.thanhtu.crud.model.dto.fk.SupplierFKDto;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private String productImage;
    private Integer discount;
    private Integer unitPrice;
    private String descriptionProduct;
}
