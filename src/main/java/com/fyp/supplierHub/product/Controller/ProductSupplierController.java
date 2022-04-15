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

    @PostMapping(value = "/save-or-edit")
    private ResponseEntity<?> saveOrEdit (@Autowired Authentication authentication ,
                                          @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productSupplierService.saveOrEdit(authentication.getName(),productDto));
    }
}
