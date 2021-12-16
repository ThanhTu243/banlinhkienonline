package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "orderdetail")
public class OrderDetailEntity {

    @EmbeddedId
    private OrderDetailIDKey id;

    private Integer quantity;
    private Integer amount;

    @ManyToOne
    @JoinColumn (name = "order_id", nullable = false,referencedColumnName = "order_id",insertable=false, updatable=false)
    @JsonBackReference
    private OrdersEntity ordersEntity;

    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false,referencedColumnName = "product_id",insertable=false, updatable=false)
    @JsonBackReference
    private ProductEntity productEntity;



}
