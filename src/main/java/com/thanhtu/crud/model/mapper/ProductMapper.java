package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.model.dto.BestSellingProducts;
import com.thanhtu.crud.model.dto.ProductPageDto;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.dto.fk.ProductFKDto;
import com.thanhtu.crud.model.request.product.ProductRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductMapper {
    public static ProductPageDto toProductPageDto(List<ProductEntity> productList,int totalPage,int currentPage)
    {
        ProductPageDto tmp=new ProductPageDto();
        tmp.setCurrentPage(currentPage);
        tmp.setTotalPage(totalPage);
        List<ProductDto> list=new ArrayList<ProductDto>();
        for(ProductEntity productEn:productList)
        {
            list.add(ProductMapper.toProductDto(productEn));
        }
        tmp.setListProduct(list);
        return tmp;
    }
    public static ProductDto toProductDto(ProductEntity productEntity)
    {
        ProductDto tmp = new ProductDto();
        tmp.setProductId(productEntity.getProductId());
        tmp.setProductName(productEntity.getProductName());
        tmp.setQuantity(productEntity.getQuantity());
        tmp.setProductImage(productEntity.getProductImage());
        tmp.setDiscount(productEntity.getDiscount());
        tmp.setUnitPrice(productEntity.getUnitPrice());
        tmp.setDescriptionProduct(productEntity.getDescriptionProduct());
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
        tmp.setIsDelete("NO");
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
        if(productEntities==null)
        {
            return list;
        }
        else{
            for(ProductEntity product:productEntities)
            {
                ProductFKDto tmp=new ProductFKDto();
                tmp.setProductId(product.getProductId());
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

    }
    public static ProductFKDto toProductFKDto(ProductEntity productEntity)
    {
        ProductFKDto tmp=new ProductFKDto();
        tmp.setProductId(productEntity.getProductId());
        tmp.setProductName(productEntity.getProductImage());
        tmp.setQuantity(productEntity.getQuantity());
        tmp.setProductImage(productEntity.getProductImage());
        tmp.setDiscount(productEntity.getDiscount());
        tmp.setUnitPrice(productEntity.getUnitPrice());
        tmp.setDescriptionProduct(productEntity.getDescriptionProduct());
        return tmp;
    }

}
