package com.fyp.supplierHub.product.Dtos;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PUBLIC_productCategoryDto {
    private Integer categoryId;

    private String categoryName;
}
