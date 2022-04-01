package com.fyp.supplierHub.customer.controller;

import com.fyp.supplierHub.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/customer")
public class CustomerController {

    private final CustomerService customerService ;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    private ResponseEntity<?> getAuthenticatedCustomer(Authentication authentication){
        return ResponseEntity.ok(customerService.getAuthenticatedCustomer(authentication.getName()));
    }

}
