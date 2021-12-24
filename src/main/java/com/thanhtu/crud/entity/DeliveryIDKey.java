package com.thanhtu.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeliveryIDKey implements Serializable {
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "shipper_id")
    private Integer shipperId;

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        DeliveryIDKey that=(DeliveryIDKey) o;
        return Objects.equals(orderId,that.orderId) && Objects.equals(shipperId,that.shipperId);
    }
    @Override
    public int hashCode(){
        return Objects.hash(orderId,shipperId);
    }
}
