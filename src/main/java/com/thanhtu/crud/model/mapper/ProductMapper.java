package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.model.dto.*;
import com.thanhtu.crud.model.dto.fk.ProductFKDto;
import com.thanhtu.crud.model.request.product.ProductImageRequest;
import com.thanhtu.crud.model.request.product.ProductRequest;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductMapper {
    public static ProductOrderDetailDto toProductOrderDetailDto(OrderDetailEntity orderDetail)
    {
        ProductOrderDetailDto tmp=new ProductOrderDetailDto();
        tmp.setProductId(orderDetail.getProductEntity().getProductId());
        tmp.setNameProduct(orderDetail.getProductEntity().getProductName());
        tmp.setPriceAfterDiscount(Long.valueOf(orderDetail.getProductEntity().getUnitPrice())*Long.valueOf(100-orderDetail.getProductEntity().getDiscount())/100);
        tmp.setQuantity(orderDetail.getQuantity());
        tmp.setAmount(orderDetail.getAmount());
        return tmp;
    }
    public static ProductViewByIdDto toProductViewByIdDto(ProductDto productDto, String rating, List<ReviewsDto> list)
    {
        ProductViewByIdDto tmp=new ProductViewByIdDto();
        tmp.setProductId(productDto.getProductId());
        tmp.setProductName(productDto.getProductName());
        tmp.setQuantity(productDto.getQuantity());
        tmp.setProductImageList(productDto.getProductImageSet());
        tmp.setDiscount(productDto.getDiscount());
        tmp.setUnitPrice(productDto.getUnitPrice());
        tmp.setPriceAfterDiscount(Long.valueOf(productDto.getPriceAfterDiscount()));
        tmp.setDescriptionProduct(productDto.getDescriptionProduct());
        tmp.setRating(rating);
        tmp.setListReviews(list);
        return tmp;
    }
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
        tmp.setDiscount(productEntity.getDiscount());
        tmp.setUnitPrice(productEntity.getUnitPrice());
        tmp.setPriceAfterDiscount(Long.valueOf(productEntity.getUnitPrice())*Long.valueOf(100-productEntity.getDiscount())/100);
        tmp.setDescriptionProduct(productEntity.getDescriptionProduct());
        tmp.setCategoryFKDto(CategoryMapper.toCategoryViewDto(productEntity.getCategoryEntity()));
        tmp.setSupplierFKDto(SupplierMapper.toSupplierViewDto(productEntity.getSupplierEntity()));
        List<ProductImageDto> productImageDtoList=new ArrayList<>();
        for(ImageEntity imageEntity:productEntity.getImageEntities())
        {
            ProductImageDto productImageDto=new ProductImageDto();
            productImageDto.setImageId(imageEntity.getImageId());
            productImageDto.setImage(imageEntity.getImage());
            productImageDtoList.add(productImageDto);
        }
        tmp.setProductImageSet(productImageDtoList);
        return tmp;
    }
    public static ProductEntity toProductEntity(ProductRequest productRequest, CategoryEntity categoryEntity, SupplierEntity supplierEntity)
    {
        ProductEntity tmp=new ProductEntity();
        tmp.setProductName(productRequest.getProductName());
        tmp.setQuantity(productRequest.getQuantity());
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
                tmp.setProductName(product.getProductName());
                tmp.setQuantity(product.getQuantity());
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
        tmp.setQuantity(productEntity.getQuantity());
        tmp.setDiscount(productEntity.getDiscount());
        tmp.setUnitPrice(productEntity.getUnitPrice());
        tmp.setDescriptionProduct(productEntity.getDescriptionProduct());
        return tmp;
    }

}
