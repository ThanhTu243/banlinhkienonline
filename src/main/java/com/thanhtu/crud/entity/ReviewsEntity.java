package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsEntity {
    @EmbeddedId
    private ReviewsIdKey id;
    private String comments;
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false,referencedColumnName = "order_id",insertable=false, updatable=false)
    @JsonBackReference
    private OrdersEntity ordersEntity;

    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false,referencedColumnName = "product_id",insertable=false, updatable=false)
    @JsonBackReference
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false,referencedColumnName = "customer_id",insertable = false,updatable = false)
    @JsonBackReference
    private CustomerEntity customerEntity;
}
