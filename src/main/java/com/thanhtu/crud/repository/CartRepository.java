package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CartEntity;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    CartEntity findCartEntitiesByCartIdAndIsDelete(int id,String status);
    List<CartEntity> findCartEntityByCustomerEntity(CustomerEntity customer);
    CartEntity findCartEntitiesByCustomerEntityAndProductEntity(CustomerEntity customer, ProductEntity product);
}
