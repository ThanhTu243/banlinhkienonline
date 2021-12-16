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
    @Column
    private Integer order_id;

    @Column
    private Integer product_id;

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        OrderDetailIDKey that=(OrderDetailIDKey) o;
        return Objects.equals(order_id,that.order_id) && Objects.equals(product_id,that.product_id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(order_id,product_id);
    }
}
