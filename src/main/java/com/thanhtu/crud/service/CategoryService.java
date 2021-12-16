package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.dto.fk.CategoryFKDto;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.model.request.CategoryUpdate;

import java.util.List;
public interface CategoryService {
    List<CategoryDto> getListCategory();
    CategoryDto getCateById(int id);
    CategoryDto createCategory(CategoryRequest categoryRequest);
    CategoryDto updateCategory(int id,CategoryRequest categoryRequest);
    CategoryDto deleteCategory(Integer id);
    List<CategoryDto> getListByCategoryName(CategoryRequest categoryRequest);
}
