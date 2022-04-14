package com.fyp.supplierHub.product.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/product-category")
public class ProductCategoryController {

    @PutMapping("/{productCategory}")
    public ResponseEntity<?> getProductsFromCategory (@PathVariable String productCategory){
        return ResponseEntity.ok(productCategory);
    }

}
