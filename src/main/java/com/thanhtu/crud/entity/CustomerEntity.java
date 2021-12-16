package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customer_id;
    private String gmailCustomer;
    private String passwordCustomer;
    private String fullnameCustomer;
    private String phonenumberCustomer;
    private String activationCode;
    private String passwordresetCode;
    private Integer activeCustomer;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CartEntity> cartEntities;

    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<OrdersEntity> ordersEntities;


}
