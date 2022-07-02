package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productimage")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productimage_id")
    private Integer productImageId;
    private String isDelete;

    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false,referencedColumnName = "product_id")
    @JsonBackReference
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn (name = "image_id", nullable = false,referencedColumnName = "image_id")
    @JsonBackReference
    private ImageEntity imageEntity;

}
