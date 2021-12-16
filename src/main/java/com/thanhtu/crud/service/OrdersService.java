package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.request.OrdersAssignRequest;
import com.thanhtu.crud.model.request.OrdersStatusRequest;
import com.thanhtu.crud.model.request.OrdersUpdateRequest;
import com.thanhtu.crud.model.request.OrdersUpdateStatusRequest;

import java.util.List;

public interface OrdersService {

    List<OrdersEntity> getListOrderByStatus(OrdersStatusRequest ordersStatusRequest);

    OrdersDto updateOrders(int id , OrdersUpdateRequest ordersUpdateRequest);

    void approvalOrders(List<OrdersUpdateStatusRequest> listId);
    List<OrdersEntity> getListOrderByStatus(String status);

    void assignOrders(List<OrdersAssignRequest> list);
}
