package com.thanhtu.crud.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class CategoryUpdate {
    @NotNull(message = "Nhập tên danh mục")
    @NotEmpty(message = "Nhập tên danh mục")
    private String categoryName;
}
