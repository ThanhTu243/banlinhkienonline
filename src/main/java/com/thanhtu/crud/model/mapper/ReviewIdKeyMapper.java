package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.ReviewsIdKey;
import com.thanhtu.crud.model.request.ReviewsCreateRequest;

public class ReviewIdKeyMapper {
    public static ReviewsIdKey toReviewsIdKey(ReviewsCreateRequest request)
    {
        ReviewsIdKey tmp=new ReviewsIdKey();
        tmp.setCustomerId(request.getCustomerId());
        tmp.setOrderId(request.getOrderId());
        tmp.setProductId(request.getProductId());
        return tmp;
    }
}
