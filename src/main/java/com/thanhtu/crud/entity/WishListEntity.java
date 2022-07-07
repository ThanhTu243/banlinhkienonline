package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name="wishlist")
@Getter
@Setter
@ToString
public class WishListEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Integer wishlistId;
    private String isDelete;

    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false,referencedColumnName = "customer_id")
    @JsonBackReference
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false,referencedColumnName = "product_id")
    @JsonBackReference
    private ProductEntity productEntity;
}
