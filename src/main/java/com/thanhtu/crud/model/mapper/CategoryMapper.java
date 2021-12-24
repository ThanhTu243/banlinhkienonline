package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.dto.fk.CategoryFKDto;
import com.thanhtu.crud.model.request.CategoryRequest;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(CategoryEntity categoryEntity)
    {
        CategoryDto tmp = new CategoryDto();
        tmp.setCategoryId(categoryEntity.getCategoryId());
        tmp.setCategoryName(categoryEntity.getCategoryName());
        tmp.setProductEntityList(ProductMapper.toProductFKDto(categoryEntity.getProductEntityList()));
        return tmp;
    }
    public static CategoryFKDto toCategoryViewDto(CategoryEntity categoryEntity)
    {
        CategoryFKDto tmp = new CategoryFKDto();
        tmp.setCategoryId(categoryEntity.getCategoryId());
        tmp.setCategoryName(categoryEntity.getCategoryName());
        return tmp;
    }
    public static CategoryEntity toCategoryEntity(CategoryRequest categoryRequest)
    {
        CategoryEntity tmp = new CategoryEntity();
        tmp.setCategoryName(categoryRequest.getCategoryName());
        tmp.setIsDelete("NO");
        return tmp;
    }

}
