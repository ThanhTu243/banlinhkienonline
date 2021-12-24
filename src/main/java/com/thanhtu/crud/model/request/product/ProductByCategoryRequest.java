package com.thanhtu.crud.model.request.product;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductByCategoryRequest {
    @NotNull(message = "Chọn danh mục")
    @NotEmpty(message = "Chọn danh mục")
    private String categoryName;
}
