package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.ReviewsEntity;
import com.thanhtu.crud.model.dto.ReviewsDto;
import com.thanhtu.crud.model.request.ReviewsCreateRequest;

public class ReviewsMapper {
    public static ReviewsEntity toReviewsEntity(ReviewsCreateRequest request, OrdersEntity orders, ProductEntity product, CustomerEntity customer)
    {
        ReviewsEntity tmp=new ReviewsEntity();
        tmp.setId(ReviewIdKeyMapper.toReviewsIdKey(request));
        tmp.setComments(request.getComments());
        tmp.setRating(request.getRating());
        tmp.setCustomerEntity(customer);
        tmp.setOrdersEntity(orders);
        tmp.setProductEntity(product);
        return tmp;
    }
    public static ReviewsDto toReviewDto(ReviewsEntity reviews)
    {
        ReviewsDto tmp=new ReviewsDto();
        tmp.setCustomerName(reviews.getCustomerEntity().getFirstnameCustomer()+" "+reviews.getCustomerEntity().getLastnameCustomer());
        tmp.setOrderId(reviews.getId().getOrderId());
        tmp.setProductName(reviews.getProductEntity().getProductName());
        tmp.setComments(reviews.getComments());
        tmp.setRating(reviews.getRating());
        return tmp;
    }
}
