package com.thanhtu.crud.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReviewsCreateRequest {
    private Integer orderId;
    private Integer productId;
    private Integer customerId;
    @NotNull(message = "Vui lòng chọn đánh giá sản phẫm")
    private Integer rating;
    @NotEmpty(message = "Vui lòng bình luận về sản phẫm")
    @NotNull(message = "Vui lòng bình luận về sản phẫm")
    @Length(min = 8,message = "Vui lòng nhâp hơn 8 ký tự")
    private String comments;
}
