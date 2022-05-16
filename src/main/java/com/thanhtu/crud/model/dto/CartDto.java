package com.thanhtu.crud.model.dto;


import com.thanhtu.crud.model.dto.fk.CustomerFKDto;
import com.thanhtu.crud.model.dto.fk.ProductFKDto;
import com.thanhtu.crud.model.dto.pk.CartIDPKDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    private CartIDPKDto id;
    private Integer quantity;
    private Long amountCart;
    private CustomerFKDto customerFKDto;
    private ProductFKDto productFKDto;
}
