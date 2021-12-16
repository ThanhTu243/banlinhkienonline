package com.thanhtu.crud.model.request;

import lombok.Data;

@Data
public class OrdersAssignRequest {
    private Integer orderId;
    private Integer shipperId;
}
