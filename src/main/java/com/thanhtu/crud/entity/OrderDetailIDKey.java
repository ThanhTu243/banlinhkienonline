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
public class OrderDetailIDKey implements Serializable {
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name="product_id")
    private Integer productId;

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        OrderDetailIDKey that=(OrderDetailIDKey) o;
        return Objects.equals(orderId,that.orderId) && Objects.equals(productId,that.productId);
    }
    @Override
    public int hashCode(){
        return Objects.hash(productId,productId);
    }
}
