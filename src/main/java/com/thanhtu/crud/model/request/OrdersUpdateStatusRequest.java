package com.thanhtu.crud.model.request;

import com.thanhtu.crud.model.dto.OrdersIdDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrdersUpdateStatusRequest {
    @NotEmpty(message = "Vui lòng chọn đơn hàng")
    private List<OrdersIdDto> list;
}
