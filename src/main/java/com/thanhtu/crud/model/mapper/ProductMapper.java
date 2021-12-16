package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.dto.fk.ProductFKDto;
import com.thanhtu.crud.model.request.ProductRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductMapper {
    public static ProductDto toProductDto(ProductEntity productEntity)
    {
        ProductDto tmp = new ProductDto();
        tmp.setProductId(productEntity.getProduct_id());
        tmp.setProductName(productEntity.getProductName());
        tmp.setQuantity(productEntity.getQuantity());
        tmp.setProductImage(productEntity.getProductImage());
        tmp.setDiscount(productEntity.getDiscount());
        tmp.setUnitPrice(productEntity.getUnitPrice());
        tmp.setDescriptionProduct(productEntity.getDescriptionProduct());
        tmp.setCategoryFKDto(CategoryMapper.toCategoryViewDto(productEntity.getCategoryEntity()));
        tmp.setSupplierFKDto(SupplierMapper.toSupplierViewDto(productEntity.getSupplierEntity()));
        return tmp;
    }
    public static ProductEntity toProductEntity(ProductRequest productRequest, CategoryEntity categoryEntity, SupplierEntity supplierEntity)
    {
        ProductEntity tmp=new ProductEntity();
        tmp.setProductName(productRequest.getProductName());
        tmp.setQuantity(productRequest.getQuantity());
        tmp.setProductImage(productRequest.getProductImage());
        tmp.setDiscount(productRequest.getDiscount());
        tmp.setUnitPrice(productRequest.getUnitPrice());
        tmp.setDescriptionProduct(productRequest.getDescriptionProduct());
        tmp.setCategoryEntity(categoryEntity);
        tmp.setSupplierEntity(supplierEntity);
        return tmp;
    }
    public static ProductEntity toUpdateProduct(ProductEntity product,ProductRequest productRequest,CategoryEntity categoryEntity, SupplierEntity supplierEntity)
    {
        product.setProductName(productRequest.getProductName());
        product.setQuantity(productRequest.getQuantity());
        product.setProductImage(productRequest.getProductImage());
        product.setDiscount(productRequest.getDiscount());
        product.setUnitPrice(productRequest.getUnitPrice());
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        product.setCategoryEntity(categoryEntity);
        product.setSupplierEntity(supplierEntity);
        return product;
    }
    public static Set<ProductFKDto> toProductFKDto(Set<ProductEntity> productEntities)
    {
        Set<ProductFKDto> list = new HashSet<ProductFKDto>();
        for(ProductEntity product:productEntities)
        {
            ProductFKDto tmp=new ProductFKDto();
            tmp.setProductId(product.getProduct_id());
            tmp.setProductName(product.getProductImage());
            tmp.setQuantity(product.getQuantity());
            tmp.setProductImage(product.getProductImage());
            tmp.setDiscount(product.getDiscount());
            tmp.setUnitPrice(product.getUnitPrice());
            tmp.setDescriptionProduct(product.getDescriptionProduct());
            list.add(tmp);
        }
        return list;
    }
    public static ProductFKDto toProductFKDto(ProductEntity productEntity)
    {
        ProductFKDto tmp=new ProductFKDto();
        tmp.setProductId(productEntity.getProduct_id());
        tmp.setProductName(productEntity.getProductImage());
        tmp.setQuantity(productEntity.getQuantity());
        tmp.setProductImage(productEntity.getProductImage());
        tmp.setDiscount(productEntity.getDiscount());
        tmp.setUnitPrice(productEntity.getUnitPrice());
        tmp.setDescriptionProduct(productEntity.getDescriptionProduct());
        return tmp;
    }

}
