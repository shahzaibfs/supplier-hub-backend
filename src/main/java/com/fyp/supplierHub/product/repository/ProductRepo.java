package com.fyp.supplierHub.product.repository;

import com.fyp.supplierHub.product.Dtos.ProductDto;
import com.fyp.supplierHub.product.enitity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(
            "SELECT p FROM Product p WHERE p.productId = ?2 and p.supplier.supplierId = ?1"
    )
    Optional<Product> getProductByIdAndAuthenticatedUser(int supplierId , int productId) ;

    @Modifying
    @Query(
            "Delete  FROM Product p WHERE p.productId = ?2 and p.supplier.supplierId = ?1"
    )
    int deleteProductById(int supplierId , int productId) ;

    @Query(
            "SELECT p FROM Product p WHERE p.supplier.supplierId = ?1"
    )
    List<Product> findAllBySupplierId(Integer id);

}
