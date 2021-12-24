package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.entity.ShipperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<ShipperEntity,Integer>{
    ShipperEntity findShipperEntityByUserShipper(String username);
    List<ShipperEntity> findShipperEntityByIsDelete(String status);
    ShipperEntity findShipperEntityByShipperIdAndIsDelete(Integer id,String status);
    List<AdminsEntity> findShipperEntityByUserShipperOrGmailShipperAndIsDelete(String username,String email,String status);
}
