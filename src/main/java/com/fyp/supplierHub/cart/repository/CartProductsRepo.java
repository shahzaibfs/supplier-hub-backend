package com.fyp.supplierHub.cart.repository;

import com.fyp.supplierHub.cart.entity.Cart;
import com.fyp.supplierHub.cart.entity.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartProductsRepo extends JpaRepository<CartProducts,Integer> {

    @Query("SELECT c FROM CartProducts c where c.product.productId = ?1 and c.cart.cartId = ?2")
    CartProducts getCartProductByProductId(Integer id,Integer cartId);

    @Modifying
    @Query("Delete from CartProducts sa Where sa.cart.cartId =?1 ")
    void deleteAllByCartId(Integer cartId);
}
