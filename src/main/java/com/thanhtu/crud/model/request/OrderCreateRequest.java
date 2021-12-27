package com.thanhtu.crud.model.request;

import com.thanhtu.crud.model.dto.ProductToOrder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderCreateRequest {
    private Integer customerId;
    private String address;
    private String phoneNumber;
    private Integer total;
    @NotEmpty(message = "Vui lòng chọn mặt hàng để ")
    private List<ProductToOrder> list;
}
