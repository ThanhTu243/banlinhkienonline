package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.request.product.ProductByCategoryRequest;
import com.thanhtu.crud.model.request.product.ProductByNameRequest;
import com.thanhtu.crud.model.request.product.ProductBySupplierRequest;
import com.thanhtu.crud.model.request.product.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductEntity> getListProduct(Pageable pageable);

    ProductDto getProductById(int id);

    ProductDto createProduct(ProductRequest productRequest);

    ProductDto updateProduct(Integer id, ProductRequest productRequest);

    ProductDto deleteProduct(Integer id);

    Page<ProductEntity> getListProductByName(ProductByNameRequest productByNameRequest, Pageable pageable);

    Page<ProductEntity> getListProductByCategory(ProductByCategoryRequest productByCategoryRequest, Pageable pageable);

    Page<ProductEntity> getListProductBySupplier(ProductBySupplierRequest productBySupplierRequest, Pageable pageable);
}
