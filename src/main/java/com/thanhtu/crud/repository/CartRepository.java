package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CartEntity;
import com.thanhtu.crud.entity.CartIDKey;
import com.thanhtu.crud.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    CartEntity findCartEntityById(CartIDKey id);
    List<CartEntity> findCartEntityByCustomerEntity(CustomerEntity customer);
}
