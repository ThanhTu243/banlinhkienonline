package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.DeliveryAddressEntity;
import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.model.dto.DeliveryAddressDto;
import com.thanhtu.crud.model.dto.ProductOrderDetailDto;
import com.thanhtu.crud.model.request.DeliveryAddressRequest;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressMapper {
    public static DeliveryAddressEntity toDeliveryAddressEntity(DeliveryAddressRequest deliveryAddressRequest, CustomerEntity customer){
        DeliveryAddressEntity tmp=new DeliveryAddressEntity();
        tmp.setPhoneNumber(deliveryAddressRequest.getPhoneNumber());
        tmp.setCustomerEntity(customer);
        tmp.setFullname(deliveryAddressRequest.getFullname());
        tmp.setDeliveryaddress(deliveryAddressRequest.getDeliveryAddress());
        tmp.setIsDelete("NO");
        return tmp;
    }

    public static List<DeliveryAddressDto> toDeliveryAddressDtoList(List<DeliveryAddressEntity> deliveryAddressEntityList)
    {
        List<DeliveryAddressDto> list=new ArrayList<>();
        for(DeliveryAddressEntity deliveryAddress:deliveryAddressEntityList)
        {
            DeliveryAddressDto tmp=new DeliveryAddressDto();
            tmp.setDeliveryAddressId(deliveryAddress.getDeliveryaddressId());
            tmp.setDeliveryAddress(deliveryAddress.getDeliveryaddress());
            tmp.setFullname(deliveryAddress.getFullname());
            tmp.setPhoneNumber(deliveryAddress.getPhoneNumber());
            list.add(tmp);
        }
        return list;
    }

    public static DeliveryAddressEntity toUpdateDeliveryAddressEntity(DeliveryAddressEntity deliveryAddress, DeliveryAddressRequest deliveryAddressRequest) {
        deliveryAddress.setDeliveryaddress(deliveryAddressRequest.getDeliveryAddress());
        deliveryAddress.setFullname(deliveryAddressRequest.getFullname());
        deliveryAddress.setPhoneNumber(deliveryAddressRequest.getPhoneNumber());
        return deliveryAddress;
    }
}
