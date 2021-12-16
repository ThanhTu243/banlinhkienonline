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
@Getter
@Setter
@Entity
@Table(name = "cart")
public class CartEntity{
    @EmbeddedId
    private CartIDKey id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false,referencedColumnName = "customer_id",insertable=false, updatable=false)
    @JsonBackReference
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false,referencedColumnName = "product_id",insertable=false, updatable=false)
    @JsonBackReference
    private ProductEntity productEntity;


}
