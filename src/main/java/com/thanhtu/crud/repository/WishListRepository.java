package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishListEntity,Integer> {
    List<WishListEntity> findWishListEntitiesByCustomerEntityAndIsDelete(CustomerEntity customer,String status);
    WishListEntity findWishListEntitiesByWishlistIdAndIsDelete(int id,String status);
}
