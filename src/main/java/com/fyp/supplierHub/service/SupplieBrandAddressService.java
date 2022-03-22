package com.fyp.supplierHub.service;

import com.fyp.supplierHub.entity.Supplier;
import com.fyp.supplierHub.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.entity.User;
import com.fyp.supplierHub.models.BrandAddressRequest;
import com.fyp.supplierHub.reposiory.SupplierBrandAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class SupplieBrandAddressService {
    private final SupplierBrandAddressRepo supplierBrandAddressRepo ;

    private final SupplierService supplierService ;
    @Autowired
    public SupplieBrandAddressService(SupplierService supplierService ,SupplierBrandAddressRepo supplierBrandAddressRepo) {
        this.supplierBrandAddressRepo = supplierBrandAddressRepo;

        this.supplierService = supplierService ;
    }


    public Collection<?> findAllByUsername(String username){
        Supplier EXISTING_SUPPLIER = supplierService.getSupplierFromDatabase(username);
        return supplierBrandAddressRepo.findAllBySupplier(EXISTING_SUPPLIER);
    }

    public SupplierBrandAddresses findOneByUsernameAndId(String username,Integer brandAddressId){
        Supplier EXISTING_SUPPLIER = supplierService.getSupplierFromDatabase(username);
        return supplierBrandAddressRepo.findBrandAddressByUsernameAndId(EXISTING_SUPPLIER.getSupplierId(),brandAddressId);
    }

    public SupplierBrandAddresses editOrSaveBrandAddress(String username, BrandAddressRequest brandAddressRequest){
        if(brandAddressRequest.getId() >0){
            SupplierBrandAddresses supplierBrandAddress = findOneByUsernameAndId(username , brandAddressRequest.getId());
            supplierBrandAddress.setAddress(brandAddressRequest.getAddress());
            supplierBrandAddress.setCity(brandAddressRequest.getCity());
            supplierBrandAddress.setPostalCode(brandAddressRequest.getPostalCode());
            return  supplierBrandAddressRepo.save(supplierBrandAddress) ;
        }
        Supplier EXISTING_SUPPLIER = supplierService.getSupplierFromDatabase(username);
        SupplierBrandAddresses supplierBrandAddress = new SupplierBrandAddresses() ;
        supplierBrandAddress.setAddress(brandAddressRequest.getAddress());
        supplierBrandAddress.setCity(brandAddressRequest.getCity());
        supplierBrandAddress.setPostalCode(brandAddressRequest.getPostalCode());
        supplierBrandAddress.setSupplier(EXISTING_SUPPLIER);


        return  supplierBrandAddressRepo.save(supplierBrandAddress) ;
    }

    @Transactional
    public void deleteBrandAddress (String username   ,  Integer brandAddressId){
        Supplier EXISTING_SUPPLIER = supplierService.getSupplierFromDatabase(username);
        System.out.println(EXISTING_SUPPLIER.getSupplierId());
        System.out.println(brandAddressId);
        supplierBrandAddressRepo.deleteBrandAddress(EXISTING_SUPPLIER.getSupplierId(),brandAddressId) ;


    }
}
