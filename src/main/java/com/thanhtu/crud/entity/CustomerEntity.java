package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    private String userCustomer;
    private String fullnameCustomer;
    private String address;
    private String gmailCustomer;
    private String phonenumberCustomer;
    private String isDelete;


    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CartEntity> cartEntities;

    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<OrdersEntity> ordersEntities;

    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ReviewsEntity> reviewsEntities;



}
