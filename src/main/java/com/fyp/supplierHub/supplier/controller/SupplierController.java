package com.fyp.supplierHub.supplier.controller;

import com.fyp.supplierHub.supplier.service.SupplierBrandAddressService;
import com.fyp.supplierHub.supplier.service.SupplierService;
import com.fyp.supplierHub.supplier.service.SupplierServiceImp;
import com.fyp.supplierHub.supplier.service.SupplierBrandAddressServiceImp;
import com.fyp.supplierHub.supplier.models.SupplierRequest;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.models.BrandAddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1.0/supplier")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierBrandAddressService supplierBrandAddressServiceImp;

    @Autowired
    public SupplierController(
            SupplierService supplierService,
            SupplierBrandAddressServiceImp supplierBrandAddressServiceImp)
    {
        this.supplierService = supplierService;
        this.supplierBrandAddressServiceImp = supplierBrandAddressServiceImp;
    }

    @GetMapping("/load-all")
    public ResponseEntity<?> loadAllSuppliers()
    {

        return ResponseEntity.ok(supplierService.loadAllSuppliers());
    }

    @GetMapping
    public ResponseEntity<Supplier> loadAuthenticatedSupplier (Authentication authentication)
    {
        return ResponseEntity.ok(supplierService.LoadAuthenticatedSupplier(authentication.getName()));
    }

    @PostMapping("/edit")
    private  ResponseEntity<?> editSupplier(Authentication authentication , @RequestBody SupplierRequest supplierRequest)
    {
        System.out.println("hy");
        return ResponseEntity.ok(supplierService.editSupplier(authentication.getName(),supplierRequest));
    }

    @GetMapping("/brand-addresses")
    private ResponseEntity<?> loadAllBrandAddresses(Authentication authentication )
    {
        return ResponseEntity.ok(supplierBrandAddressServiceImp.findAllByUsername(authentication.getName()));
    }

    @PostMapping("/brand-address")
    private ResponseEntity<?> saveOrEditBrandAddress (Authentication authentication, @RequestBody BrandAddressRequest brandAddressRequest)
    {
        System.out.println("i am shahzaib");
        return ResponseEntity.ok(supplierBrandAddressServiceImp.editOrSaveBrandAddress(authentication.getName(),brandAddressRequest));
    }

    @DeleteMapping("/brand-address/{brandAddressId}")
    private ResponseEntity<?> deleteBrandAddress (Authentication authentication, @PathVariable(value = "brandAddressId") Integer brandAddressId)
    {
        System.out.println("i am shahzaib");
        supplierBrandAddressServiceImp.deleteBrandAddress(authentication.getName(),brandAddressId);
        return ResponseEntity.ok( "Deleted Successfully");
    }


}
