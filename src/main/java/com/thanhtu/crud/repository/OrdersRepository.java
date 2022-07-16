package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity,Integer>{
    List<OrdersEntity> findOrdersEntityByCustomerEntityAndStatusOrderOrderByCreateDateDesc(CustomerEntity customer,String status);
    Page<OrdersEntity> findOrdersEntityByStatusOrderOrderByCreateDateDesc(String status,Pageable page);
    List<OrdersEntity> findOrdersEntitiesByStatusOrderOrderByCreateDate(String status);
    OrdersEntity findOrdersEntityByOrderId(int id);
    OrdersEntity findOrdersEntityByOrderIdAndStatusOrder(int id,String status);
    List<OrdersEntity> findOrdersEntityByCreateDateBetweenAndStatusOrder(Timestamp from,Timestamp to,String status);
    List<OrdersEntity> findOrdersEntityByCreateDateBetweenAndPaymentStatus(Timestamp from,Timestamp to,String paymentStatus);
    Integer countAllByStatusOrder(String status);
    Integer countAllByCustomerEntityAndStatusOrder(CustomerEntity customer,String status);
    List<OrdersEntity> findOrdersEntityByPaymentStatus(String status);
}
