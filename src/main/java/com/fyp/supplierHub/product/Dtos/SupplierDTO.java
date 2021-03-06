package com.fyp.supplierHub.product.Dtos;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  SupplierDTO {
    private int supplierId ;

    private String brandName;
    private String supplierProfileUrl ;
}