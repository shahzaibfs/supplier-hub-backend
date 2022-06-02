package com.fyp.supplierHub.order.dtos;

import com.fyp.supplierHub.product.Dtos.PUBLIC_productCategoryDto;
import com.fyp.supplierHub.product.Dtos.SupplierDTO;
import com.fyp.supplierHub.product.enitity.OutOfStock;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PublicProductDetails {

    private int productId ;
    private SupplierDTO supplier ;

}
