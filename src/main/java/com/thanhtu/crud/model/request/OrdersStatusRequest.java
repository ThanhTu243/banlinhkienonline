package com.thanhtu.crud.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrdersStatusRequest {
    @NotBlank(message = "Vui lòng nhập trạng thái")
    private String statusOrder;
}
