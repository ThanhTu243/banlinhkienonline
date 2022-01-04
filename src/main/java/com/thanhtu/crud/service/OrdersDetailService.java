package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.model.dto.OrderDetailViewDto;
import com.thanhtu.crud.model.dto.OrdersDetailDto;

import java.util.List;

public interface OrdersDetailService {

    OrderDetailViewDto detailOrders(Integer id);
    void cancelOrdersDetail(int orderId,int productId);
}
