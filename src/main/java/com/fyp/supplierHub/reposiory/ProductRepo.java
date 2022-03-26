package com.fyp.supplierHub.reposiory;

import com.fyp.supplierHub.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(
            "SELECT p FROM Product p WHERE p.productId = ?2 and p.supplier.supplierId = ?1"
    )
    Product getProductByIdAndAuthenticatedUser(int supplierId , int productId) ;

    @Modifying
    @Query(
            "Delete  FROM Product p WHERE p.productId = ?2 and p.supplier.supplierId = ?1"
    )
    int deleteProductById(int supplierId , int productId) ;

}
