package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    private String categoryName;
    private String isDelete;

    @OneToMany(mappedBy = "categoryEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ProductEntity> productEntityList;

    public CategoryEntity(String categoryName) {
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", productEntityList=" + productEntityList +
                '}';
    }
}
