package com.fyp.supplierHub.service;

import com.fyp.supplierHub.entity.Supplier;
import com.fyp.supplierHub.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.entity.User;
import com.fyp.supplierHub.models.SupplierRequest;
import com.fyp.supplierHub.reposiory.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupplierService {
    private final SupplierRepo supplierRepo;
    private final MyUserDetailService myUserDetailService ;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo, MyUserDetailService myUserDetailService) {
        this.supplierRepo = supplierRepo;
        this.myUserDetailService = myUserDetailService;
    }



    public  Collection<?> loadAllSuppliers(){
        return supplierRepo.findAll();
    }
    public  Object LoadSupplierByUser(User user){
        return supplierRepo.findSupplierByUser(user);
    }

    public Supplier getSupplierFromDatabase (String userName){
        User EXISTING_USER = myUserDetailService.getUserFromDatabase(userName);
        return supplierRepo.findSupplierByUser(EXISTING_USER);

    }



    public Object editSupplier(String  userName , SupplierRequest supplierRequest){

        Supplier EXISTING_SUPPLIER  =  getSupplierFromDatabase(userName);

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
        EXISTING_SUPPLIER
                .setSupplierBrandAddresses(
                        new HashSet<SupplierBrandAddresses>(Arrays.asList(supplierBrandAddress))
                );

        return supplierRepo.save(EXISTING_SUPPLIER) ;
    }

}
