package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "supplier")
@Entity
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer supplierId;
    private String supplierName;
    private String isDelete;

    @OneToMany(mappedBy = "supplierEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ProductEntity> productEntitySet;


}
