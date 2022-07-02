package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.model.dto.fk.CategoryFKDto;
import com.thanhtu.crud.model.dto.fk.SupplierFKDto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Integer discount;
    private Integer unitPrice;
    private Long priceAfterDiscount;
    private String descriptionProduct;
    private CategoryFKDto categoryFKDto;
    private SupplierFKDto supplierFKDto;
    private List<ProductImageDto> productImageSet;
}
