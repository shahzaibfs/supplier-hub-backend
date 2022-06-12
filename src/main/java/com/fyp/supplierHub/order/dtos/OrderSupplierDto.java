package com.fyp.supplierHub.order.dtos;


import com.fyp.supplierHub.order.entity.Orders;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.supplier.entity.Supplier;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrderSupplierDto {


    private  Integer orderId;
    private Integer quantity;

    private String status ;

    private PublicProductDetails product;
    private OrdersDto orders;
}
