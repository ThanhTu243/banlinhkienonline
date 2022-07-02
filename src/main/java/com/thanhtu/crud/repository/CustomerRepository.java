package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer>{
    CustomerEntity findCustomerEntityByUserCustomer(String username);
    Page<CustomerEntity> findCustomerEntityByIsDelete(String status, Pageable page);
    CustomerEntity findCustomerEntityByCustomerIdAndIsDelete(int id,String status);
    Integer countAllByIsDelete(String status);
    CustomerEntity findCustomerEntitiesByGmailCustomerAndIsDelete(String email,String status);
}
