package com.thanhtu.crud.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrdersAssignRequest {
    @NotNull(message =  "Chọn đơn hàng")
    @NotEmpty(message = "Chọn đơn hàng")
    private Integer orderId;
    @NotNull(message =  "Chọn shipper")
    @NotEmpty(message =  "Chọn shipper")
    private Integer shipperId;
}
