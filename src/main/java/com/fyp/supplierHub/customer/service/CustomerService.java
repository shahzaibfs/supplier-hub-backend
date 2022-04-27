package com.fyp.supplierHub.customer.service;

import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.models.CustomerEditDto;
import com.fyp.supplierHub.customer.models.CustomerModel;

public interface CustomerService {

    CustomerModel getCustomerFromDatabase(String username);
    Customer getAuthenticatedCustomer(String username);

    CustomerEditDto editCustomerDetails(String username, CustomerEditDto customerEditDto);
}
