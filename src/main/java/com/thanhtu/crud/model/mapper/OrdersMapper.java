package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.dto.OrdersPageDto;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.dto.ProductPageDto;
import com.thanhtu.crud.model.dto.fk.OrdersFKDto;
import com.thanhtu.crud.model.request.OrderCreateRequest;
import com.thanhtu.crud.model.request.OrdersUpdateRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdersMapper {
    public static OrdersDto toOrdersDto(OrdersEntity ordersEntity)
    {
        OrdersDto tmp=new OrdersDto();
        tmp.setOrderId(ordersEntity.getOrderId());
        tmp.setPhoneNumber(ordersEntity.getPhoneNumber());
        tmp.setTotalAmount(ordersEntity.getTotalAmount());
        tmp.setAddress(ordersEntity.getAddress());
        tmp.setCreateDate(ordersEntity.getCreateDate().toString());
        tmp.setStatusOrder(ordersEntity.getStatusOrder());
        tmp.setCustomerFKDto(CustomerMapper.toCustomerFKDto(ordersEntity.getCustomerEntity()));
        return tmp;
    }
    public static OrdersEntity toUpdateOrders(OrdersEntity ordersEntity, OrdersUpdateRequest ordersUpdateRequest)
    {
        ordersEntity.setAddress(ordersUpdateRequest.getAddress());
        ordersEntity.setPhoneNumber(ordersUpdateRequest.getPhoneNumber());
        return ordersEntity;
    }
    public static OrdersFKDto toOrdersFKDto(OrdersEntity ordersEntity)
    {
        OrdersFKDto tmp=new OrdersFKDto();
        tmp.setOrderId(ordersEntity.getOrderId());
        tmp.setAddress(ordersEntity.getAddress());
        tmp.setPhoneNumber(ordersEntity.getPhoneNumber());
        tmp.setCreateDate(ordersEntity.getCreateDate().toString());
        tmp.setTotalAmount(ordersEntity.getTotalAmount());
        tmp.setStatusOrder(ordersEntity.getStatusOrder());
        return tmp;
    }
    public static OrdersEntity toCreateOrders(OrderCreateRequest orderCreateRequest, CustomerEntity customer,String paymentMehtod)
    {
        OrdersEntity tmp=new OrdersEntity();
        tmp.setAddress(orderCreateRequest.getAddress());
        tmp.setPhoneNumber(orderCreateRequest.getPhoneNumber());
        tmp.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        tmp.setTotalAmount(orderCreateRequest.getTotal());
        tmp.setStatusOrder("Chưa duyệt");
        tmp.setNote("");
        tmp.setPaymentMethod(paymentMehtod);
        tmp.setPaymentStatus("Chưa thanh toán");
        tmp.setCustomerEntity(customer);
        return tmp;
    }

    public static OrdersEntity toCreateOrders(OrderCreateRequest orderCreateRequest, CustomerEntity customer,String paymentMethod,String paymentStatus)
    {
        OrdersEntity tmp=new OrdersEntity();
        tmp.setAddress(orderCreateRequest.getAddress());
        tmp.setPhoneNumber(orderCreateRequest.getPhoneNumber());
        tmp.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        tmp.setTotalAmount(orderCreateRequest.getTotal());
        tmp.setStatusOrder("Chưa duyệt");
        tmp.setNote("");
        tmp.setPaymentMethod(paymentMethod);
        tmp.setPaymentStatus(paymentStatus);
        tmp.setCustomerEntity(customer);
        return tmp;
    }
    public static OrdersPageDto toOrdersPageDto(List<OrdersEntity> ordersList, int totalPage, int currentPage)
    {
        OrdersPageDto tmp=new OrdersPageDto();
        tmp.setCurrentPage(currentPage);
        tmp.setTotalPage(totalPage);
        List<OrdersDto> list=new ArrayList<OrdersDto>();
        for(OrdersEntity orders:ordersList)
        {
            list.add(OrdersMapper.toOrdersDto(orders));
        }
        tmp.setListOrders(list);
        return tmp;
    }
}
