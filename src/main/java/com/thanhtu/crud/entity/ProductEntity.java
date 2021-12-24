package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "product")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    private String productName;
    private Integer quantity;
    private String productImage;
    private Integer discount;
    private Integer unitPrice;
    private String descriptionProduct;
    private String isDelete;
    @ManyToOne
    @JoinColumn (name = "category_id", nullable = false,referencedColumnName = "category_id")
    @JsonBackReference
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false,referencedColumnName = "supplier_id")
    @JsonBackReference
    private SupplierEntity supplierEntity;

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CartEntity> cartEntities;

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<OrderDetailEntity> productEntities;


}
