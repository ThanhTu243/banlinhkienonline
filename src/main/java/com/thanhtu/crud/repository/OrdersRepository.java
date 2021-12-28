package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity,Integer>{
    List<OrdersEntity> findOrdersEntityByCustomerEntityAndStatusOrder(CustomerEntity customer,String status);
    List<OrdersEntity> findOrdersEntityByStatusOrder(String status);
    OrdersEntity findOrdersEntityByOrderId(int id);
    OrdersEntity findOrdersEntityByOrderIdAndStatusOrder(int id,String status);
    List<OrdersEntity> findOrdersEntityByCreateDateBetweenAndStatusOrder(Timestamp from,Timestamp to,String status);
}
