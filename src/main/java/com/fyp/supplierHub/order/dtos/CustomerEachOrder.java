package com.fyp.supplierHub.order.dtos;

import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.supplier.entity.Supplier;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CustomerEachOrder {
    private  Integer orderId;
    private Integer quantity;

    private String status ;


    private PublicSupplierOrder supplier;


    private PublicProductDetails product;
}
