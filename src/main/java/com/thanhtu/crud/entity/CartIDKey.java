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
public class CartIDKey implements Serializable{
    @Column
    private Integer customer_id;
    @Column
    private Integer product_id;
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        CartIDKey that=(CartIDKey) o;
        return Objects.equals(customer_id,that.customer_id) && Objects.equals(product_id,that.product_id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(customer_id,product_id);
    }


}
