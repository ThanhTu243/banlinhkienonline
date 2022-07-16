package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.request.ChangeIsDeleteRequest;
import com.thanhtu.crud.model.request.product.ProductByCategoryRequest;
import com.thanhtu.crud.model.request.product.ProductByNameRequest;
import com.thanhtu.crud.model.request.product.ProductBySupplierRequest;
import com.thanhtu.crud.model.request.product.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<ProductEntity> getListProduct(Pageable pageable);

    ProductDto getProductById(int id);
    ProductDto getProductByIdAdmin(int id);

    ProductDto createProduct(ProductRequest productRequest);

    ProductDto updateProduct(Integer id, ProductRequest productRequest);

    ProductDto deleteProduct(Integer id);

    Page<ProductEntity> getListProductByName(ProductByNameRequest productByNameRequest, Pageable pageable);

    Page<ProductEntity> getListProductByCategory(ProductByCategoryRequest productByCategoryRequest, Pageable pageable);
    Page<ProductEntity> getListProductByCategoryName(String categoryName, Pageable pageable);

    Page<ProductEntity> getListProductBySupplier(ProductBySupplierRequest productBySupplierRequest, Pageable pageable);

    Page<ProductEntity> getListProductBySupplierName(String supplierName, Pageable pageable);
    Page<ProductEntity> getListProductByKeyWord(String keyword,Pageable page);

    Page<ProductEntity> getListProductByKeyWord1(String keyword,Pageable page);
    Page<ProductEntity> getListProductByKeyWord1Admin(String keyword, Pageable pageable);

    Page<ProductEntity> getListProductByKeyWordAndCategory(String keyword, String categoryName, Pageable pageable);

    Page<ProductEntity> getListProductByKeyWordAndSupplier(String keyword, String supplier, Pageable pageable);

    Page<ProductEntity> getListProductByCategoryAndSupplier(String category, String supplier, Pageable pageable);

    Page<ProductEntity> getListProductByCategoryAndSupplierAndKeyword(String category, String supplier, String keyword, Pageable pageable);

    List<ProductDto> getTop10DiscountProduct();

    void changeIsDelete(Integer id, ChangeIsDeleteRequest changeIsDeleteRequest);

}