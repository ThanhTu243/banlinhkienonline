package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    Page<ProductEntity> findProductEntityByIsDelete(String status, Pageable page);
    ProductEntity findProductEntityByProductIdAndIsDelete(int id,String status);
    List<ProductEntity> findProductEntityByIsDelete(String status);
    Page<ProductEntity> findProductEntityByProductNameContainsAndIsDelete(String name,String status,Pageable pageable);
    Page<ProductEntity> findProductEntityByCategoryEntityAndIsDelete(CategoryEntity categoryEntity,String status,Pageable pageable);

    Page<ProductEntity> findProductEntityBySupplierEntityAndIsDelete(SupplierEntity supplier, String status, Pageable pageable);
    Page<ProductEntity> findProductEntityByProductNameLike(String keyword,Pageable pageable);

    Page<ProductEntity> findAllByProductNameContainsAndIsDelete(String keyword,String status,Pageable page);
    Page<ProductEntity> findAllByProductNameContainsAndCategoryEntityAndIsDelete(String keyword,CategoryEntity categoryEntity,String status,Pageable page);
    Page<ProductEntity> findAllByProductNameContainsAndSupplierEntityAndIsDelete(String keyword,SupplierEntity supplierEntity,String status,Pageable page);
    Page<ProductEntity> findAllByCategoryEntityAndSupplierEntityAndIsDelete(CategoryEntity categoryEntity,SupplierEntity supplierEntity,String status,Pageable page);
    Page<ProductEntity> findAllByProductNameContainsAndCategoryEntityAndSupplierEntityAndIsDelete(String keyword,CategoryEntity categoryEntity,SupplierEntity supplierEntity,String status,Pageable page);
}
