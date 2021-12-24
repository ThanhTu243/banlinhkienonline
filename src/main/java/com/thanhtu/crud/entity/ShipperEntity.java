package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shipper")
public class ShipperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipper_id")
    private Integer shipperId;
    private String userShipper;
    private String fullnameShipper;
    private String gmailShipper;
    private String isDelete;

    @OneToMany(mappedBy = "shipperEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<DeliveryEntity> deliveryEntities;

}
