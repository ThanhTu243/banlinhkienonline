package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.model.dto.ProductDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductPageDto {
    private Integer currentPage;
    private Integer totalPage;
    List<ProductDto> listProduct;
}
