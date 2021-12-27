package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity,Integer>{
    List<ReviewsEntity> findReviewsEntityByProductEntity(ProductEntity product);
    double countAllByProductEntity(ProductEntity product);
}
