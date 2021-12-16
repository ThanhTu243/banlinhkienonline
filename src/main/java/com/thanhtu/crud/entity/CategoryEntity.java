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
    private Integer category_id;

    private String categoryName;

    @OneToMany(mappedBy = "categoryEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ProductEntity> productEntityList;

    public CategoryEntity(String categoryName) {
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "category_id=" + category_id +
                ", categoryName='" + categoryName + '\'' +
                ", productEntityList=" + productEntityList +
                '}';
    }
}
