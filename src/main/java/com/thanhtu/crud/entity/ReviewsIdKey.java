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
public class ReviewsIdKey implements Serializable {
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        DeliveryIDKey that=(DeliveryIDKey) o;
        return Objects.equals(orderId,orderId) && Objects.equals(productId,productId) && Objects.equals(customerId,customerId);
    }
    @Override
    public int hashCode(){
        return Objects.hash(orderId,productId,customerId);
    }
}
