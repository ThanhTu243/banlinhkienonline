package com.thanhtu.crud.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrdersPageDto {
    private Integer currentPage;
    private Integer totalPage;
    List<OrdersDto> listOrders;
}
