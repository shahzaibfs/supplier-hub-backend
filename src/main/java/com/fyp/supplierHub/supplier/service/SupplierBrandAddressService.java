package com.fyp.supplierHub.supplier.service;

import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.models.BrandAddressRequest;

import java.util.Collection;
import java.util.List;

public interface SupplierBrandAddressService {

    Collection<?> findAllByUsername(String username);

    SupplierBrandAddresses findOneByUsernameAndId(String username, Integer brandAddressId);

    SupplierBrandAddresses editOrSaveBrandAddress(String username, BrandAddressRequest brandAddressRequest);

    void deleteBrandAddress (String username   ,  Integer brandAddressId);

    List<SupplierBrandAddresses> searchBrandAddress (String username ,String city);
}
