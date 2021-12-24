package com.thanhtu.crud.model.request.product;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductBySupplierRequest {
    @NotNull(message = "Chọn nhà cung cấp")
    @NotEmpty(message = "Chọn nhà cung cấp")
    private String supplierName;
}
