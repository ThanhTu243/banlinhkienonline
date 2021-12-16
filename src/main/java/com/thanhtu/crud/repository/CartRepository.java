package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
}
