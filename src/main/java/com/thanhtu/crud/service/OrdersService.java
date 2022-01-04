package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.model.dto.OrderDetailView;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.dto.ProductToReview;
import com.thanhtu.crud.model.request.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdersService {

    Page<OrdersEntity> getListOrderByStatus(String status, Pageable page);

    OrdersDto updateOrders(int id , OrdersUpdateRequest ordersUpdateRequest);

    void approvalOrders(OrdersUpdateStatusRequest ordersUpdateStatusRequest);
    List<OrdersEntity> getListOrderByStatus(String status);

//    void assignOrders(List<OrdersAssignRequest> list);

    void cancelOrder(Integer id);

    OrdersDto createOrders(OrderCreateRequest orderCreateRequest);

    OrdersDto createOrdersPaypal(OrderCreateRequest orderCreateRequest);

    void confirmPaymentAndSendMail(int OrderId);

    void sendEmailOrder();

    List<OrderDetailView> getOrderDetailByCustomerIdAndStatus(int id,OrdersStatusRequest ordersStatusRequest);

    void orderDelivered(OrdersUpdateStatusRequest ordersUpdateStatusRequest);

    List<ProductToReview> getOrderDetailByCustomerToReview(int id, String statusOrder);
}
