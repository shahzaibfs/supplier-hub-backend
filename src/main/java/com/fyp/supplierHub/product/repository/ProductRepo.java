package com.fyp.supplierHub.product.repository;

import com.fyp.supplierHub.product.Dtos.ProductDto;
import com.fyp.supplierHub.product.enitity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "SELECT p FROM Product p WHERE p.outOfStock = NULL AND p.supplier.supplierId = ?1"
    )
    List<Product> findAllWithSupplierIdAndOutOfStockNull(Integer id);

    @Query(
            "SELECT p FROM Product p WHERE p.outOfStock != NULL AND p.supplier.supplierId = ?1"
    )
    List<Product> getAllSupplierProductsInOutOfStockTable(Integer id);


    List<Product> findAllByIsNewProduct(boolean isNewProduct);

    @Query("SELECT p FROM Product p WHERE " +
            "p.productName LIKE CONCAT('%',:query, '%')" +
            "Or p.productDesc LIKE CONCAT('%', :query, '%')"+
            "Or p.category.categoryName LIKE CONCAT('%', :query, '%')")
    Page<Product> searchProducts(String query, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE " +
            "p.category.categoryId = ?1 ")
    Page<Product> searchProductByCategory(Integer query, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.outOfStock = NULL  AND " +
            " p.supplier.supplierId = :id and " +
            "p.productName LIKE CONCAT('%',:query, '%')"  +
            "or p.productDesc LIKE CONCAT('%', :query, '%')"+
            "or p.category.categoryName LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(Integer id,String query);
}
