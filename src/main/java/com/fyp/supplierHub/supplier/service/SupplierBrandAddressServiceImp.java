package com.fyp.supplierHub.supplier.service;

import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.repository.SupplierBrandAddressRepo;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.models.BrandAddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class SupplierBrandAddressServiceImp implements SupplierBrandAddressService{
    private final SupplierBrandAddressRepo supplierBrandAddressRepo ;

    private final SupplierServiceImp supplierServiceImp;
    @Autowired
    public SupplierBrandAddressServiceImp(SupplierServiceImp supplierServiceImp, SupplierBrandAddressRepo supplierBrandAddressRepo) {
        this.supplierBrandAddressRepo = supplierBrandAddressRepo;

        this.supplierServiceImp = supplierServiceImp;
    }


    @Override
    public Collection<?> findAllByUsername(String username){
        Supplier EXISTING_SUPPLIER = supplierServiceImp.LoadAuthenticatedSupplier(username);
        return supplierBrandAddressRepo.findAllBySupplier(EXISTING_SUPPLIER);
    }

    @Override
    public SupplierBrandAddresses findOneByUsernameAndId(String username, Integer brandAddressId){
        Supplier EXISTING_SUPPLIER = supplierServiceImp.LoadAuthenticatedSupplier(username);
        return supplierBrandAddressRepo.findBrandAddressByUsernameAndId(EXISTING_SUPPLIER.getSupplierId(),brandAddressId);
    }

    @Override
    public SupplierBrandAddresses editOrSaveBrandAddress(String username, BrandAddressRequest brandAddressRequest){
        if(brandAddressRequest.getId() >0){
            SupplierBrandAddresses supplierBrandAddress = findOneByUsernameAndId(username , brandAddressRequest.getId());
            supplierBrandAddress.setAddress(brandAddressRequest.getAddress());
            supplierBrandAddress.setCity(brandAddressRequest.getCity());
            supplierBrandAddress.setPostalCode(brandAddressRequest.getPostalCode());
            return  supplierBrandAddressRepo.save(supplierBrandAddress) ;
        }
        Supplier EXISTING_SUPPLIER = supplierServiceImp.LoadAuthenticatedSupplier(username);
        SupplierBrandAddresses supplierBrandAddress = new SupplierBrandAddresses() ;
        supplierBrandAddress.setAddress(brandAddressRequest.getAddress());
        supplierBrandAddress.setCity(brandAddressRequest.getCity());
        supplierBrandAddress.setPostalCode(brandAddressRequest.getPostalCode());
        supplierBrandAddress.setSupplier(EXISTING_SUPPLIER);


        return  supplierBrandAddressRepo.save(supplierBrandAddress) ;
    }

    @Override
    @Transactional
    public void deleteBrandAddress (String username   ,  Integer brandAddressId){
        Supplier EXISTING_SUPPLIER = supplierServiceImp.LoadAuthenticatedSupplier(username);
        System.out.println(EXISTING_SUPPLIER.getSupplierId());
        System.out.println(brandAddressId);
        supplierBrandAddressRepo.deleteBrandAddress(EXISTING_SUPPLIER.getSupplierId(),brandAddressId) ;

    }


}
