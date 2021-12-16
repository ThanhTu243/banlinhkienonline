package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.mapper.CategoryMapper;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.model.request.CategoryUpdate;
import com.thanhtu.crud.repository.CategoryRepository;
import com.thanhtu.crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService_impl implements CategoryService  {
    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public List<CategoryDto> getListCategory()
    {
        List<CategoryEntity> categoryEntities=categoryRepo.findAll();
        List<CategoryDto> categoryDtos=new ArrayList<CategoryDto>();
        for(CategoryEntity categoryEntity:categoryEntities)
        {
            categoryDtos.add(CategoryMapper.toCategoryDto(categoryEntity));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto getCateById(int id)  {
        CategoryEntity categoryEntity=categoryRepo.findById(id).orElseThrow(()-> new NotFoundException("Danh mục không tồn tại với id: "+id));
        return CategoryMapper.toCategoryDto(categoryEntity);
    }

    @Override
    public CategoryDto createCategory(CategoryRequest categoryRequest) {
        List<CategoryEntity> list=categoryRepo.findAll();
        for(CategoryEntity categoryEn:list)
        {
            if(categoryEn.getCategoryName().equals(categoryRequest.getCategoryName()))
            {
                throw new DuplicateRecoredException("Trùng tên danh mục");
            }
        }
        CategoryEntity categoryEntity=categoryRepo.save(new CategoryEntity(categoryRequest.getCategoryName()));
        return CategoryMapper.toCategoryDto(categoryEntity);
    }

    @Override
    public CategoryDto updateCategory(int id,CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity=categoryRepo.findById(id).orElseThrow(()-> new NotFoundException("Danh mục không tồn tại với id: "+id));
        if(!categoryEntity.getCategoryName().equals(categoryRequest.getCategoryName()))
        {
            List<CategoryEntity> list=categoryRepo.findAll();
            for(CategoryEntity categoryEn:list)
            {
                if(categoryEn.getCategoryName().equals(categoryRequest.getCategoryName()))
                {
                    throw new DuplicateRecoredException("Trùng tên danh mục");
                }
            }
        }
        categoryEntity.setCategoryName(categoryRequest.getCategoryName());
        CategoryEntity category=categoryRepo.save(categoryEntity);
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto deleteCategory(Integer id) {
        CategoryEntity cate1=categoryRepo.findById(id).orElseThrow(()-> new NotFoundException("Danh mục không tồn tại với id: "+id));
        categoryRepo.deleteById(id);
        return null;
    }

    @Override
    public List<CategoryDto> getListByCategoryName(CategoryRequest categoryRequest) {
        List<CategoryEntity> list=categoryRepo.findCategoryEntityByCategoryName(categoryRequest.getCategoryName());
        List<CategoryDto> list1=new ArrayList<CategoryDto>();
        for(CategoryEntity categoryEntity:list)
        {
            list1.add(CategoryMapper.toCategoryDto(categoryEntity));
        }
        return list1;
    }
}
