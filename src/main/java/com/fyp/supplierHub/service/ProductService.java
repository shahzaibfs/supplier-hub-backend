package com.fyp.supplierHub.service;

import com.fyp.supplierHub.entity.Product;
import com.fyp.supplierHub.entity.Supplier;
import com.fyp.supplierHub.reposiory.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo ;
    private final SupplierService supplierService ;


    @Autowired
    public ProductService(ProductRepo productRepo,SupplierService supplierService) {
        this.productRepo = productRepo;
        this.supplierService=supplierService;
    }

    public Collection<Product> loadAll (){
        return productRepo.findAll();
    }

    public Product saveOrEdit(String username  ,Product product){
        Supplier EXISTING_SUPPLIER =  supplierService.LoadAuthenticatedSupplier(username);
        if(product.getProductId() > 0){
            Product EXISTING_PRODUCT = productRepo.getProductByIdAndAuthenticatedUser(
                    EXISTING_SUPPLIER.getSupplierId(),
                    product.getProductId()
            );
            if(EXISTING_PRODUCT != null){
                product.setSupplier(EXISTING_SUPPLIER);
               return productRepo.save(product);
            }else{
                throw new RuntimeException("Id not found ") ;
            }

        }
        product.setSupplier(EXISTING_SUPPLIER);
        return productRepo.save(product) ;



    }

    public Product loadOne (Integer id){
        Optional <Product> EXISTING_PRODUCT = Optional.ofNullable(productRepo.findById(id)
                .orElseThrow(()-> new IllegalStateException("Product Not found")));
        return EXISTING_PRODUCT.get() ;
    }

    @Transactional
    public int deleteOne (String username,Integer productId){
        Supplier EXISTING_SUPPLIER = supplierService.LoadAuthenticatedSupplier(username);
        System.out.println(productId+""+EXISTING_SUPPLIER.getSupplierId());
        int idDeleted  = productRepo.deleteProductById(EXISTING_SUPPLIER.getSupplierId(),productId);
        if(idDeleted>0){
            return idDeleted ;
        }else{
            throw new RuntimeException("product Not found and thus It's Not deleted ") ;
        }

    }

}
