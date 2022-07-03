package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.DeliveryAddressEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.DeliveryAddressDto;
import com.thanhtu.crud.model.mapper.DeliveryAddressMapper;
import com.thanhtu.crud.model.request.DeliveryAddressRequest;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.repository.DeliveryAddressRepository;
import com.thanhtu.crud.service.AccountsService;
import com.thanhtu.crud.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService_impl implements DeliveryAddressService {
    @Autowired
    private DeliveryAddressRepository deliveryAddressRepo;
    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public List<DeliveryAddressDto> getDeliveryAddressByCustomer(int customerId) {
        CustomerEntity customerEntity= customerRepo.findCustomerEntityByCustomerIdAndIsDelete(customerId,"NO");
        if(customerEntity==null)
        {
            throw new NotFoundException("Không tìm thấy khách hàng có Id: "+customerId);
        }
        List<DeliveryAddressEntity> deliveryAddressEntityList=deliveryAddressRepo.findDeliveryAddressEntitiesByCustomerEntityAndIsDelete(customerEntity,"NO");
        return DeliveryAddressMapper.toDeliveryAddressDtoList(deliveryAddressEntityList);
    }

    @Override
    public void createDeliveryAddress(DeliveryAddressRequest deliveryAddressRequest) {
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(deliveryAddressRequest.getCustomerId(),"NO");
        if(customer==null)
        {
            throw new NotFoundException("Không tìm thấy khách hàng có Id: "+deliveryAddressRequest.getCustomerId());
        }
        deliveryAddressRepo.save(DeliveryAddressMapper.toDeliveryAddressEntity(deliveryAddressRequest,customer));
    }

    @Override
    public void updateDeliveryAddress(DeliveryAddressRequest deliveryAddressRequest,int deliveryId) {
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(deliveryAddressRequest.getCustomerId(),"NO");
        DeliveryAddressEntity deliveryAddress=deliveryAddressRepo.findDeliveryAddressEntitiesByDeliveryaddressId(deliveryId);
        if(customer==null)
        {
            throw new NotFoundException("Không tìm thấy khách hàng có Id: "+deliveryAddressRequest.getCustomerId());
        }

        if(deliveryAddress==null)
        {
            throw new NotFoundException("Không tìm thấy địa chỉ giao hàng có Id: "+deliveryId);
        }
        deliveryAddressRepo.save(DeliveryAddressMapper.toUpdateDeliveryAddressEntity(deliveryAddress,deliveryAddressRequest));
    }

    @Override
    public void deleteDeliveryAddressById(Integer customerId, Integer deliveryId) {
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(customerId,"NO");
        DeliveryAddressEntity deliveryAddress=deliveryAddressRepo.findDeliveryAddressEntitiesByDeliveryaddressId(deliveryId);
        if(customer==null)
        {
            throw new NotFoundException("Không tìm thấy khách hàng có Id: "+customerId);
        }

        if(deliveryAddress==null)
        {
            throw new NotFoundException("Không tìm thấy địa chỉ giao hàng có Id: "+deliveryId);
        }
        deliveryAddress.setIsDelete("YES");
        deliveryAddressRepo.save(deliveryAddress);
    }
}
