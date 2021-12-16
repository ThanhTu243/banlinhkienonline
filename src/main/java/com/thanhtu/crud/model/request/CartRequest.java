package com.thanhtu.crud.model.request;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartRequest {
    @NotNull(message = "Vui lòng chọn khách hàng")
    private Integer customerId;

    @NotNull(message = "Vui lòng chọn sản phẫm")
    private Integer productId;

    @NotNull(message = "Nhập số lượng sản phẫm")
    @Digits(message = "Vui lòng nhập số",fraction = 0,integer = 5)
    @Min(value =0,message = "Nhập số dương")
    @Max(value = 10000,message = "Số lượng nhỏ hơn 10000")
    private Integer quantity;
}
