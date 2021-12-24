package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.OrdersDetailDto;
import com.thanhtu.crud.model.mapper.OrdersDetailMapper;
import com.thanhtu.crud.repository.OrdersDetailRepository;
import com.thanhtu.crud.repository.OrdersRepository;
import com.thanhtu.crud.repository.ProductRepository;
import com.thanhtu.crud.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrdersDetailService_imp implements OrdersDetailService {
    @Autowired
    OrdersDetailRepository ordersDetailRepo;
    @Autowired
    OrdersRepository ordersRepo;
    @Autowired
    ProductRepository proRepo;

    @Override
    public List<OrdersDetailDto> detailOrders(Integer id) {
        OrdersEntity ordersEntity=ordersRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại đơn hàng với id: "+id));
        List<OrderDetailEntity> orderDetailEntities=ordersDetailRepo.findOrderDetailEntityByOrdersEntity(ordersEntity);
        List<OrdersDetailDto> ordersDetailDtos=new ArrayList<OrdersDetailDto>();
        for(OrderDetailEntity orderDetailEntity:orderDetailEntities)
        {
            ProductEntity productEntity=proRepo.getById(orderDetailEntity.getId().getProductId());
            ordersDetailDtos.add(OrdersDetailMapper.toOrdersDetailDto(orderDetailEntity,productEntity));
        }
        return ordersDetailDtos;
    }
}
