package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deliveryaddress")
public class DeliveryAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deliveryaddress_id")
    private Integer deliveryaddressId;
    private String fullname;
    private String phoneNumber;
    private String deliveryaddress;
    private String isDelete;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false,referencedColumnName = "customer_id")
    @JsonBackReference
    private CustomerEntity customerEntity;
}
