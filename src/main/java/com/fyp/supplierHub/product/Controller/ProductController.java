package com.fyp.supplierHub.product.Controller;


import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/load-all")
    public ResponseEntity<?> loadAll(){
        return  ResponseEntity.ok(productService.loadAll());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> loadOne(@PathVariable Integer productId) throws NotFoundException {
        if(productId <0){
            throw new NotFoundException("ID Not Found!","api/v1.0/product/"+productId);
        }
        return  ResponseEntity.ok(productService.loadOne(productId));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteOne(@Autowired Authentication authentication, @PathVariable Integer productId){
        return  ResponseEntity.ok(productService.deleteOne(authentication.getName(),productId));
    }
}