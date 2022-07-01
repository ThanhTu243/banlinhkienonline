package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customerdeliveryaddress")
public class CustomerDeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerdeliveryaddress_id")
    private Integer customerdeliveryaddressId;
    private String isDelete;

    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false,referencedColumnName = "customer_id")
    @JsonBackReference
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn (name = "deliveryaddress_id", nullable = false,referencedColumnName = "deliveryaddress_id")
    @JsonBackReference
    private DeliveryAddressEntity deliveryaddress;

}
