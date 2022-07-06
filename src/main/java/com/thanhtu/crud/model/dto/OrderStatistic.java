package com.thanhtu.crud.model.dto;

import lombok.Data;

@Data
public class OrderStatistic {
    private Integer quantityOfApprovedOrder;
    private Integer quantityOfTheOrderBeApprove;
    private Integer quantityOfDeliveredOrder;
    private Integer quantityOfCancelOrder;
}
