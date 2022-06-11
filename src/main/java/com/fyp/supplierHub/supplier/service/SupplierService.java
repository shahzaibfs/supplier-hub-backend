package com.fyp.supplierHub.supplier.service;

import com.fyp.supplierHub.product.Dtos.PUBLIC_ProductDTO;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.models.PublicSupplierData;
import com.fyp.supplierHub.supplier.models.SupplierRequest;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface SupplierService {

    Collection<?> loadAllSuppliers();

    Supplier LoadAuthenticatedSupplier(String username);

    Object editSupplier(String  userName , SupplierRequest supplierRequest);

    SupplierBrandAddresses getMainBrandAddress(Integer supplierId);

}
