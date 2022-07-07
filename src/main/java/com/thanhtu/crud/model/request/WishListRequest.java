package com.thanhtu.crud.model.request;

import lombok.Data;

@Data
public class WishListRequest {
    private Integer customerId;
    private Integer productId;
}
