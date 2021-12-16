package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
}
