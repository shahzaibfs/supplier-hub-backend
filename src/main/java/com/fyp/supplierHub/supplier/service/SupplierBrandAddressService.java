package com.fyp.supplierHub.supplier.service;

import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.models.BrandAddressRequest;

import java.util.Collection;

public interface SupplierBrandAddressService {

    Collection<?> findAllByUsername(String username);

    SupplierBrandAddresses findOneByUsernameAndId(String username, Integer brandAddressId);

    SupplierBrandAddresses editOrSaveBrandAddress(String username, BrandAddressRequest brandAddressRequest);

    void deleteBrandAddress (String username   ,  Integer brandAddressId);
}
