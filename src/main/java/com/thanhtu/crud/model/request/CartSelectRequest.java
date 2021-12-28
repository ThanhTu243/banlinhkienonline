package com.thanhtu.crud.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class CartSelectRequest {
    @NotNull(message = "Nhập Id khách hàng")
    private Integer customerId;
    @NotNull(message = "Chọn sản phẫm để thanh toán")
    @NotEmpty(message = "Chọn sản phẫm đê thanh toán")
    private List<ProductSelectCartRequest> listProducts;
}
