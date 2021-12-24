package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierEntity,Integer> {
    List<SupplierEntity> findSupplierEntityByIsDelete(String status);
    SupplierEntity findSupplierEntityBySupplierIdAndIsDelete(int id,String status);
    SupplierEntity findSupplierEntityBySupplierNameAndIsDelete(String name,String status);
}
