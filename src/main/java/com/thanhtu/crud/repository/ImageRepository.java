package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.ImageEntity;
import com.thanhtu.crud.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
    List<ImageEntity> findImageEntitiesByProductEntityAndIsDelete(ProductEntity product, String status);
}
