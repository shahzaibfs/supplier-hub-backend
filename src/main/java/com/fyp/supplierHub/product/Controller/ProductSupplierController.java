package com.fyp.supplierHub.product.Controller;

import com.fyp.supplierHub.product.Dtos.ProductDto;
import com.fyp.supplierHub.product.Dtos.ProductOutOfStockReqDto;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.services.ProductService;
import com.fyp.supplierHub.product.services.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private  ResponseEntity<?> deleteOne(@Autowired Authentication authentication, @PathVariable Integer productId){
        return  ResponseEntity.ok(productSupplierService.deleteOne(authentication.getName(),productId));
    }

    @PostMapping("/update-to-outOfStock")
    private ResponseEntity<ProductDto> updateToOutOfStock (Authentication authentication
            ,@RequestBody ProductOutOfStockReqDto productOutOfStockReqDto)
    {

        return ResponseEntity.ok(
                productSupplierService.updateToOutOfStockTable(authentication.getName()
                        ,productOutOfStockReqDto)
        );
    }

    @DeleteMapping("/remove-from-outOfStock/{productId}")
    private ResponseEntity<ProductDto> removeFromoutOfstock (Authentication authentication,
                                                             @PathVariable int productId){
        return ResponseEntity.ok(
                productSupplierService.removeFromOutOfStock(authentication.getName(),
                        productId)
        );
    }

    @GetMapping("/get-all-outOfStock-products")
    private ResponseEntity <List<ProductDto>> getAllOutOfStockProducts (Authentication authentication){
        return ResponseEntity.ok(productSupplierService.getAllOutOfStockProducts(authentication.getName()));
    }
}
