package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.entity.ShipperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends JpaRepository<ShipperEntity,Integer>{
}
