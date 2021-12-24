package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    Page<ProductEntity> findProductEntityByIsDelete(String status, Pageable page);
    ProductEntity findProductEntityByProductIdAndIsDelete(int id,String status);
    List<ProductEntity> findProductEntityByIsDelete(String status);
    Page<ProductEntity> findProductEntityByProductNameContainsAndIsDelete(String name,String status,Pageable pageable);
    Page<ProductEntity> findProductEntityByCategoryEntityAndIsDelete(CategoryEntity categoryEntity,String status,Pageable pageable);

    Page<ProductEntity> findProductEntityBySupplierEntityAndIsDelete(SupplierEntity supplier, String status, Pageable pageable);
}
