package com.fyp.supplierHub.supplier.service;

import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.models.SupplierRequest;
import com.fyp.supplierHub.supplier.repository.SupplierBrandAddressRepo;
import com.fyp.supplierHub.supplier.repository.SupplierRepo;
import com.fyp.supplierHub.user.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupplierServiceImp implements SupplierService{
    private final SupplierRepo supplierRepo;
    private final MyUserDetailService myUserDetailService ;
    private final SupplierBrandAddressRepo supplierBrandAddressRepo ;

    @Autowired
    public SupplierServiceImp(SupplierRepo supplierRepo, MyUserDetailService myUserDetailService ,
                              SupplierBrandAddressRepo supplierBrandAddressRepo) {
        this.supplierRepo = supplierRepo;
        this.myUserDetailService = myUserDetailService;
        this.supplierBrandAddressRepo = supplierBrandAddressRepo ;
    }

    @Override
    public  Collection<?> loadAllSuppliers(){

        return supplierRepo.findAll();
    }
    @Override
    public  Supplier LoadAuthenticatedSupplier(String username){
        Supplier EXISTING_SUPPLIER = supplierRepo.findSupplierByUsername(username) ;
        SupplierBrandAddresses MAIN_SUPPLIER_ADDRESS = getMainBrandAddress(EXISTING_SUPPLIER.getSupplierId());
        EXISTING_SUPPLIER.setSupplierBrandAddresses(new HashSet<>(Arrays.asList(MAIN_SUPPLIER_ADDRESS)));
        return EXISTING_SUPPLIER;
    }
    @Override
    public  Object editSupplier(String  userName , SupplierRequest supplierRequest){

        Supplier EXISTING_SUPPLIER  =  LoadAuthenticatedSupplier(userName);

        EXISTING_SUPPLIER.setSupplierFirstname(supplierRequest.getFirstName());
        EXISTING_SUPPLIER.setSupplierLastname(supplierRequest.getLastName());
        EXISTING_SUPPLIER.setSupplierProfileUrl(supplierRequest.getProfileUrl());
        EXISTING_SUPPLIER.setSupplierBio(supplierRequest.getBio());
        EXISTING_SUPPLIER.setSupplierContactNo(supplierRequest.getContactNo());
        EXISTING_SUPPLIER.setBrandName(supplierRequest.getBrandName());
        EXISTING_SUPPLIER.setBrandOwnerName(supplierRequest.getBrandOwnerName());

        SupplierBrandAddresses supplierBrandAddress = supplierRequest.getBrandAddress();
        supplierBrandAddress.setMain(true);
        supplierBrandAddress.setSupplier(EXISTING_SUPPLIER);

        SupplierBrandAddresses MAIN_SUPPLIER_ADDRESS = getMainBrandAddress(EXISTING_SUPPLIER.getSupplierId());

        if(MAIN_SUPPLIER_ADDRESS != null){
            supplierBrandAddress.setSupplierBrandAddressId(MAIN_SUPPLIER_ADDRESS.getSupplierBrandAddressId());
            EXISTING_SUPPLIER
                    .setSupplierBrandAddresses(
                            new HashSet<>(Arrays.asList(supplierBrandAddress))
                    );
        }else{
            EXISTING_SUPPLIER
                    .setSupplierBrandAddresses(
                            new HashSet<>(Arrays.asList(supplierBrandAddress))
                    );
        }

        if(EXISTING_SUPPLIER.getFacebook() != null){
            EXISTING_SUPPLIER.getFacebook().setFacebookUrl(supplierRequest.getFacebook().getFacebookUrl());
        }else{
            EXISTING_SUPPLIER.setFacebook(supplierRequest.getFacebook());

        }
        if(EXISTING_SUPPLIER.getInstagram() != null ){
            EXISTING_SUPPLIER.getInstagram().setInstagram_url(supplierRequest.getInstagram().getInstagram_url());
        }else{
            EXISTING_SUPPLIER.setInstagram(supplierRequest.getInstagram());

        }

        return supplierRepo.save(EXISTING_SUPPLIER) ;
    }
    @Override
    public SupplierBrandAddresses getMainBrandAddress(Integer supplierId){
        return supplierBrandAddressRepo.findMainAddress(supplierId,true);
    }

}
