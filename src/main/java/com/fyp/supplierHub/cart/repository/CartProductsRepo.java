package com.fyp.supplierHub.cart.repository;

import com.fyp.supplierHub.cart.entity.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductsRepo extends JpaRepository<CartProducts,Integer> {

    @Query("SELECT c FROM CartProducts c where c.product.productId = ?1 and c.cart.cartId = ?2")
    CartProducts getCartProductByProductId(Integer id,Integer cartId);
}
