package com.fyp.supplierHub.supplier.service;

import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.models.SupplierRequest;

import java.util.Collection;

public interface SupplierService {

    Collection<?> loadAllSuppliers();

    Supplier LoadAuthenticatedSupplier(String username);

    Object editSupplier(String  userName , SupplierRequest supplierRequest);

    SupplierBrandAddresses getMainBrandAddress(Integer supplierId);
}
