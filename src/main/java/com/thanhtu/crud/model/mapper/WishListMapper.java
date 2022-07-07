package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.model.dto.*;
import com.thanhtu.crud.model.dto.fk.ProductFKDto;
import com.thanhtu.crud.model.request.product.ProductRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WishListMapper {
    public static WishListEntity toWishListEntity(CustomerEntity customer,ProductEntity product)
    {
        WishListEntity tmp=new WishListEntity();
        tmp.setCustomerEntity(customer);
        tmp.setProductEntity(product);
        tmp.setIsDelete("NO");
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
            list.add(WishListMapper.toProductDto(productEn));
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
            if(imageEntity.getIsDelete().equals("NO"))
            {
                ProductImageDto productImageDto=new ProductImageDto();
                productImageDto.setImageId(imageEntity.getImageId());
                productImageDto.setImage(imageEntity.getImage());
                productImageDtoList.add(productImageDto);
            }
        }
        tmp.setProductImageSet(productImageDtoList);
        return tmp;
    }

    public static ProductDto toProductDto(ProductEntity productEntity,List<ImageEntity> list)
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
        for(ImageEntity imageEntity:list)
        {
            ProductImageDto productImageDto=new ProductImageDto();
            productImageDto.setImageId(imageEntity.getImageId());
            productImageDto.setImage(imageEntity.getImage());
            productImageDtoList.add(productImageDto);
        }
        tmp.setProductImageSet(productImageDtoList);
        return tmp;
    }


    public static WishListDto toWishListDto(WishListEntity wishList) {
        WishListDto tmp=new WishListDto();
        tmp.setWishlistId(wishList.getWishlistId());
        ProductDto productDto=ProductMapper.toProductDto(wishList.getProductEntity());
        tmp.setProduct(productDto);
        return tmp;
    }
}
