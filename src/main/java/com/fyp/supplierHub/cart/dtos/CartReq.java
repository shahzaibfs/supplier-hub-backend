package com.fyp.supplierHub.cart.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartReq {

    private Integer quantity ;
    private Integer productId ;

}
