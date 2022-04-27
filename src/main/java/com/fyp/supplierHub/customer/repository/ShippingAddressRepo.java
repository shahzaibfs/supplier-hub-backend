package com.fyp.supplierHub.customer.repository;

import com.fyp.supplierHub.customer.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressRepo extends JpaRepository<ShippingAddress,Integer> {


    @Query("Select sa from ShippingAddress sa Where sa.customer.customerName=?1")
    List<ShippingAddress> getAllByAuthenticatedCustomer(String username);
}
