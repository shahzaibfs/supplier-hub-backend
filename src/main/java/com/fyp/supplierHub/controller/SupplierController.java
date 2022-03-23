package com.fyp.supplierHub.controller;

import com.fyp.supplierHub.entity.Supplier;
import com.fyp.supplierHub.models.BrandAddressRequest;
import com.fyp.supplierHub.models.SupplierRequest;
import com.fyp.supplierHub.reposiory.SupplierBrandAddressRepo;
import com.fyp.supplierHub.service.SupplieBrandAddressService;
import com.fyp.supplierHub.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/supplier")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplieBrandAddressService supplieBrandAddressService ;

    @Autowired
    public SupplierController(SupplierService supplierService ,SupplieBrandAddressService supplieBrandAddressService ) {
        this.supplierService = supplierService;
        this.supplieBrandAddressService = supplieBrandAddressService ;
    }

    @GetMapping("/load-all")
    public ResponseEntity<?> loadAllSuppliers(){
        return ResponseEntity.ok(supplierService.loadAllSuppliers());
    }

    @GetMapping
    public ResponseEntity<Supplier> loadAuthenticatedSupplier (Authentication authentication){
        return ResponseEntity.ok(supplierService.LoadAuthenticatedSupplier(authentication.getName()));
    }

    @PostMapping("/edit")
    private  ResponseEntity<?> editSupplier(Authentication authentication , @RequestBody SupplierRequest supplierRequest){

        return ResponseEntity.ok(supplierService.editSupplier(authentication.getName(),supplierRequest));
    }

    @GetMapping("/brand-addresses")
    private ResponseEntity<?> loadAllBrandAddresses(Authentication authentication ){
        return ResponseEntity.ok(supplieBrandAddressService.findAllByUsername(authentication.getName()));
    }

    @PostMapping("/brand-address")
    private ResponseEntity<?> saveOrEditBrandAddress (Authentication authentication, @RequestBody BrandAddressRequest brandAddressRequest){
        System.out.println("i am shahzaib");
        return ResponseEntity.ok(supplieBrandAddressService.editOrSaveBrandAddress(authentication.getName(),brandAddressRequest));
    }

    @DeleteMapping("/brand-address/{brandAddressId}")
    private ResponseEntity<?> deleteBrandAddress (Authentication authentication, @PathVariable(value = "brandAddressId") Integer brandAddressId){
        System.out.println("i am shahzaib");
        supplieBrandAddressService.deleteBrandAddress(authentication.getName(),brandAddressId);
        return ResponseEntity.ok( "Deleted Successfully");
    }
}
