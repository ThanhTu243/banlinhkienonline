package com.thanhtu.crud.service;

import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> getListProduct();

    ProductDto getProductById(int id);

    ProductDto createProduct(ProductRequest productRequest);

    ProductDto updateProduct(Integer id, ProductRequest productRequest);

    ProductDto deleteProduct(Integer id);
}
