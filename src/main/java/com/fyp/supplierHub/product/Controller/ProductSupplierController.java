package com.fyp.supplierHub.product.Controller;

import com.fyp.supplierHub.product.Dtos.ProductDto;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.services.ProductService;
import com.fyp.supplierHub.product.services.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1.0/product-supplier")
public class ProductSupplierController {

    private final ProductSupplierService productSupplierService ;

    @Autowired
    public ProductSupplierController(ProductSupplierService productSupplierService) {
        this.productSupplierService = productSupplierService;
    }

    @GetMapping(value = "/get-all-products")
    private ResponseEntity<?> getAllSupplierProducts (Authentication authentication){
        return ResponseEntity.ok(productSupplierService.getAllSupplierProducts(authentication.getName()));
    }

    @PostMapping(value = "/save-or-edit")
    private ResponseEntity<?> saveOrEdit (@Autowired Authentication authentication ,
                                          @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productSupplierService.saveOrEdit(authentication.getName(),productDto));
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<?> deleteOne(@Autowired Authentication authentication, @PathVariable Integer productId){
        return  ResponseEntity.ok(productSupplierService.deleteOne(authentication.getName(),productId));
    }
}
