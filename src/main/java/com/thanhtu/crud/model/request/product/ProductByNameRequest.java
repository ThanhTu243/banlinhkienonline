package com.thanhtu.crud.model.request.product;

import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductByNameRequest {
    @NotNull(message = "Nhập tên sản phẫm")
    @NotEmpty(message = "Nhập tên sản phẫm")
    @Size(max =1000,message = "Nhập tên sản phẫm nhỏ hơn 100 ký tự")
    private String productName;
}
