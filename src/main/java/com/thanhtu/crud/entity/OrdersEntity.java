package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    private String address;
    private String phoneNumber;
    @Column(columnDefinition = "Date")
    private Timestamp createDate;
    private Long totalAmount;
    private String note;
    private String paymentMethod;
    private String paymentStatus;
    private String statusOrder;


    @OneToMany(mappedBy = "ordersEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<OrderDetailEntity> orderDetailEntities;

    @OneToMany(mappedBy = "ordersEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ReviewsEntity> reviewsEntities;

    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false,referencedColumnName = "customer_id")
    @JsonBackReference
    private CustomerEntity customerEntity;

}
