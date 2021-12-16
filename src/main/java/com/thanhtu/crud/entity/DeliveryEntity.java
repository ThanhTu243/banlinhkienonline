package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "delivery")
public class DeliveryEntity implements Serializable {
    @EmbeddedId
    private DeliveryIDKey id;

    @ManyToOne
    @JoinColumn (name = "order_id", nullable = false,referencedColumnName = "order_id",insertable=false, updatable=false)
    @JsonBackReference
    private OrdersEntity ordersEntity;

    @ManyToOne
    @JoinColumn (name = "shipper_id", nullable = false,referencedColumnName = "shipper_id",insertable=false, updatable=false)
    @JsonBackReference
    private ShipperEntity shipperEntity;

}
