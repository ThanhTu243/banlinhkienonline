package com.thanhtu.crud.entity;

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
    private String deliveryaddress;
    private String isDelete;

    @OneToMany(mappedBy = "deliveryAddressEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CustomerDeliveryAddress> customerDeliveryAddresses;
}
