package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.OrderDetailViewDto;
import com.thanhtu.crud.model.dto.OrdersDetailDto;
import com.thanhtu.crud.model.dto.ProductOrderDetailDto;
import com.thanhtu.crud.model.mapper.OrdersDetailMapper;
import com.thanhtu.crud.model.mapper.ProductMapper;
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
    public OrderDetailViewDto detailOrders(Integer id) {
        OrdersEntity ordersEntity=ordersRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại đơn hàng với id: "+id));
        List<OrderDetailEntity> orderDetailEntities=ordersDetailRepo.findOrderDetailEntityByOrdersEntity(ordersEntity);
        List<ProductOrderDetailDto> list=new ArrayList<ProductOrderDetailDto>();
        for(OrderDetailEntity orderDetailEntity:orderDetailEntities)
        {
            list.add(ProductMapper.toProductOrderDetailDto(orderDetailEntity));
        }
        OrderDetailViewDto ordersDetailDtos=new OrderDetailViewDto();
        ordersDetailDtos.setOrderId(id);
        ordersDetailDtos.setNameCustomer(ordersEntity.getCustomerEntity().getFullnameCustomer());
        ordersDetailDtos.setTotalAmount(ordersEntity.getTotalAmount());
        ordersDetailDtos.setList(list);
        return ordersDetailDtos;
    }

    @Override
    public void cancelOrdersDetail(int orderId, int productId) {
        OrdersEntity ordersEntity=ordersRepo.findOrdersEntityByOrderId(orderId);
        if(ordersEntity==null)
        {
            throw new NotFoundException("Không có đơn hàng này");
        }
        ProductEntity product= proRepo.findProductEntityByProductIdAndIsDelete(productId,"NO");
        if(product==null)
        {
            throw new NotFoundException("Không có sản phẫm này");
        }
        OrderDetailEntity orderDetailEntity=ordersDetailRepo.findOrderDetailEntityByOrdersEntityAndProductEntity(ordersEntity,product);
        orderDetailEntity.setIsDelete("YES");
        ordersDetailRepo.save(orderDetailEntity);
        if(ordersDetailRepo.countAllByOrdersEntity(ordersEntity)==0)
        {
            ordersEntity.setStatusOrder("Đã hủy");
            ordersRepo.save(ordersEntity);
        }
    }
}
