package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.OrdersDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrderDetailEntity,Integer>{
    List<OrderDetailEntity> findOrderDetailEntityByOrdersEntity(OrdersEntity ordersEntity);
    OrderDetailEntity findOrderDetailEntityByOrdersEntityAndProductEntity(OrdersEntity orders, ProductEntity product);
    Integer countAllByOrdersEntity(OrdersEntity orders);
}
