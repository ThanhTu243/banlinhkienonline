package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.ReviewsDto;
import com.thanhtu.crud.model.mapper.ReviewsMapper;
import com.thanhtu.crud.model.request.ReviewsCreateRequest;
import com.thanhtu.crud.repository.*;
import com.thanhtu.crud.service.AccountsService;
import com.thanhtu.crud.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewsService_impl implements ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepo;
    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private OrdersDetailRepository ordersDetailRepo;

    @Override
    public ReviewsDto createReviews(ReviewsCreateRequest request) {
        OrdersEntity orders=ordersRepo.findOrdersEntityByOrderId(request.getOrderId());
        ProductEntity product= productRepo.findProductEntityByProductIdAndIsDelete(request.getProductId(), "NO");
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(request.getCustomerId(), "NO");
        ReviewsEntity reviews= reviewsRepo.save(ReviewsMapper.toReviewsEntity(request,orders,product,customer));
        OrderDetailEntity orderDetailEntity=ordersDetailRepo.findOrderDetailEntityByOrdersEntityAndProductEntity(orders,product);
        orderDetailEntity.setIsReview("YES");
        ordersDetailRepo.save(orderDetailEntity);
        return ReviewsMapper.toReviewDto(reviews);
    }

    @Override
    public String getRating(int productId) {
        ProductEntity product=productRepo.findProductEntityByProductId(productId);
        List<ReviewsEntity> list=reviewsRepo.findReviewsEntityByProductEntity(product);
        double sumRating=0;
        double avgRating=0;
        for(ReviewsEntity reviews:list)
        {
            sumRating+=reviews.getRating();
        }
        double numberRating=reviewsRepo.countAllByProductEntity(product);
        avgRating=sumRating/numberRating;
        return String.format("%.1f", avgRating);
    }

    @Override
    public List<ReviewsDto> getAllCommentsByProductId(int id) {
        ProductEntity product=productRepo.findProductEntityByProductId(id);
        List<ReviewsEntity> list=reviewsRepo.findReviewsEntityByProductEntity(product);
        List<ReviewsDto> listReview=new ArrayList<ReviewsDto>();
        for(ReviewsEntity reviews:list)
        {
            listReview.add(ReviewsMapper.toReviewDto(reviews));
        }
        return listReview;
    }
}
