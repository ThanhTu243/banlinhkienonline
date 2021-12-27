package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.entity.ReviewsEntity;
import com.thanhtu.crud.model.dto.ReviewsDto;
import com.thanhtu.crud.model.request.ReviewsCreateRequest;

import java.util.List;

public interface ReviewsService {
    ReviewsDto createReviews(ReviewsCreateRequest request);
    String getRating(int productId);

    List<ReviewsDto> getAllCommentsByProductId(int id);
}
