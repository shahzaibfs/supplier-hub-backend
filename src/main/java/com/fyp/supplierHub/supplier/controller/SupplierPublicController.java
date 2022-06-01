package com.fyp.supplierHub.supplier.controller;

import com.fyp.supplierHub.supplier.models.PublicSupplierData;
import com.fyp.supplierHub.supplier.service.PublicSupplierService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1.0/supplier-public")
public class SupplierPublicController {

    private final PublicSupplierService publicSupplierService ;

    public SupplierPublicController(PublicSupplierService publicSupplierService) {
        this.publicSupplierService = publicSupplierService;
    }

    @GetMapping("{id}")
    private ResponseEntity<PublicSupplierData> getSupplier(@PathVariable Integer id){

        return ResponseEntity.ok(publicSupplierService.getSupplier(id)) ;
    }

}
