package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.model.dto.DeliveryAddressDto;
import com.thanhtu.crud.model.request.DeliveryAddressRequest;

import java.util.List;

public interface DeliveryAddressService {
    List<DeliveryAddressDto> getDeliveryAddressByCustomer(int customerId);

    void createDeliveryAddress(DeliveryAddressRequest deliveryAddressRequest);

    void updateDeliveryAddress(DeliveryAddressRequest deliveryAddressRequest,int deliveryId);

    void deleteDeliveryAddressById(Integer customerId, Integer deliveryId);
}
