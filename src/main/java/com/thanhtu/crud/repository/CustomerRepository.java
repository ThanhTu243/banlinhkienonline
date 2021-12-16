package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer>{
}
