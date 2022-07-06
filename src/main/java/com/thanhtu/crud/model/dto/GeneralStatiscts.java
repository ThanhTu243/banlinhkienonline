package com.thanhtu.crud.model.dto;

import lombok.Data;

@Data
public class GeneralStatiscts {
    private Integer numberOfCustomer;
    private Integer newOrders;
    private Integer totalProduct;
    private Long revenue;
    private Integer quantityOfApprovedOrder;
    private Integer quantityOfDeliveredOrder;
    private Integer quantityOfCancelOrder;
}
