package com.fyp.supplierHub.product.services;

import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.repository.ProductRepo;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.service.SupplierServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo ;
    private final SupplierServiceImp supplierServiceImp;


    @Autowired
    public ProductService(ProductRepo productRepo, SupplierServiceImp supplierServiceImp) {
        this.productRepo = productRepo;
        this.supplierServiceImp = supplierServiceImp;
    }

    public Collection<Product> loadAll (){
        return productRepo.findAll();
    }



    public Product loadOne (Integer id){
        Optional <Product> EXISTING_PRODUCT = Optional.ofNullable(productRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("ID Not Found!","api/v1.0/product/"+id)));
        return EXISTING_PRODUCT.get() ;
    }



}
