package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddressEntity,Integer> {
    List<DeliveryAddressEntity> findDeliveryAddressEntitiesByCustomerEntityAndIsDelete(CustomerEntity customer,String status);
    DeliveryAddressEntity findDeliveryAddressEntitiesByDeliveryaddressId(Integer id);
}
