package com.fyp.supplierHub.cart.repository;

import com.fyp.supplierHub.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {

    @Query("SELECT c from Cart c where c.customer.customer_id =?1")
    Cart getCartById (Integer id );
}
