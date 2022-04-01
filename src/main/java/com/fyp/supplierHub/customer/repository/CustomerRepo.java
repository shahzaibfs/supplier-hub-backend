package com.fyp.supplierHub.customer.repository;

import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    @Query("SELECT c from Customer c where c.user.userName =?1")
    CustomerModel getCustomerModelByUsername(String username);
    @Query("SELECT c from Customer c where c.user.userName =?1")
    Customer getCustomerByUsername(String username);
}
