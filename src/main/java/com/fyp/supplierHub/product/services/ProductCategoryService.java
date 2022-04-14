package com.fyp.supplierHub.product.services;

import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.repository.ProductRepo;

import java.util.Arrays;
import java.util.List;

public class ProductCategoryService {

    private final ProductRepo productRepo ;

    public ProductCategoryService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<?> getProductsFromCategory (String productCategory){
        return Arrays.asList("ok sir ia m here ", "i am here too ");
    }
}
