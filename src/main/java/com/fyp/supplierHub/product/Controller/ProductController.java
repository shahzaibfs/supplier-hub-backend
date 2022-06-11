package com.fyp.supplierHub.product.Controller;


import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.product.Dtos.PUBLIC_ProductDTO;
import com.fyp.supplierHub.product.Dtos.SearchProductReq;
import com.fyp.supplierHub.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1.0/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/load-all")
    public ResponseEntity<Collection<PUBLIC_ProductDTO>> loadAll(){
        return  ResponseEntity.ok(productService.loadAll());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<PUBLIC_ProductDTO> loadOne(@PathVariable Integer productId) throws NotFoundException {
        if(productId <0){
            throw new NotFoundException("ID Not Found!","api/v1.0/product/"+productId);
        }
        return  ResponseEntity.ok(productService.loadOne(productId));
    }

    @GetMapping("/load-all-new-products")
    public ResponseEntity<Collection<PUBLIC_ProductDTO>> loadAll_newProducts()
    {
        return  ResponseEntity.ok(productService.loadAll_newProducts());
    }

    @PostMapping("/searchProduct")
    public ResponseEntity<Page<PUBLIC_ProductDTO>> searchProduct (@RequestBody SearchProductReq searchProductReq)
    {
        return ResponseEntity.ok(productService.searchProduct(searchProductReq.getQuery()));
    }
}
