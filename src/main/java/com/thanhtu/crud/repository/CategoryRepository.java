package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer>{
    CategoryEntity findCategoryEntityByCategoryNameAndIsDelete(String name,String status);
    List<CategoryEntity> findCategoryEntityByIsDelete(String status);
    CategoryEntity findCategoryEntityByCategoryIdAndIsDelete(Integer id,String status);
}
