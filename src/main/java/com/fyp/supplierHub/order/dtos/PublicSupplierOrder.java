package com.fyp.supplierHub.order.dtos;

import com.fyp.supplierHub.supplier.entity.Facebook;
import com.fyp.supplierHub.supplier.entity.Instagram;
import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.models.PublicSupplierProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PublicSupplierOrder {

    private int supplierId ;

    private String supplierProfileUrl ;
    private String supplierContactNo;



}
