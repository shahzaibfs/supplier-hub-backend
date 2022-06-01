package com.fyp.supplierHub.product.Dtos;


import com.fyp.supplierHub.product.enitity.OutOfStock;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PUBLIC_ProductDTO {
    private int productId ;
    private int productSize;
    private int productMinOrder ;
    private long productPrice;

    private String productName ;
    private String productDesc;
    private String productDetails;
    private String productCoverUrl ;
    private String productWeight;


    private OutOfStock outOfStock ;
    private SupplierDTO supplier ;
    private PUBLIC_productCategoryDto productCategory;


}


