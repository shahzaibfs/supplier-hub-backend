package com.fyp.supplierHub.service;

import com.fyp.supplierHub.entity.User;
import com.fyp.supplierHub.reposiory.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SupplierService {
    private final SupplierRepo supplierRepo;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public final Collection<?> loadAllSuppliers(){
        return supplierRepo.findAll();
    }
    public final Object LoadSupplierByUserId(User user){
        return supplierRepo.findSupplierByUser(user);
    }
}
