package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.model.dto.fk.CustomerFKDto;
import com.thanhtu.crud.model.dto.fk.OrdersFKDto;
import com.thanhtu.crud.model.dto.fk.ProductFKDto;
import lombok.Data;

@Data
public class OrdersDetailDto {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Long amount;
    private ProductFKDto productFKDto;
}
