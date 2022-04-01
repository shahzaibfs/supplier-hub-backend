package com.fyp.supplierHub.customer.service;

import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.models.CustomerModel;
import com.fyp.supplierHub.customer.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImp implements CustomerService{
    private final CustomerRepo customerRepo ;

    @Autowired
    public CustomerServiceImp(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerModel getCustomerFromDatabase(String username) {
        return null;
    }

    @Override
    public Customer getAuthenticatedCustomer(String username) {
        return customerRepo.getCustomerByUsername(username);
    }
}
