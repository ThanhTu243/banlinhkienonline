package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity,Integer>{
    List<OrdersEntity> findOrdersEntityByStatusOrder(String status);
    List<OrdersEntity> findOrdersEntityByCreateDate(Timestamp from);
}
