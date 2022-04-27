package com.fyp.supplierHub.customer.service;

import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.models.CustomerEditDto;
import com.fyp.supplierHub.customer.models.CustomerModel;
import com.fyp.supplierHub.customer.repository.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImp implements CustomerService{
    private final CustomerRepo customerRepo ;
    private final ModelMapper modelMapper ;

    @Autowired
    public CustomerServiceImp(CustomerRepo customerRepo,ModelMapper modelMapper)
    {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerModel getCustomerFromDatabase(String username) {
        return null;
    }

    @Override
    public Customer getAuthenticatedCustomer(String username) {
        return customerRepo.getCustomerByUsername(username);
    }

    @Override
    public CustomerEditDto editCustomerDetails(String username, CustomerEditDto customerEditDto) {
        Customer Existing_Customer = getAuthenticatedCustomer(username);
        customerEditDto.setCustomer_id(Existing_Customer.getCustomer_id());
        modelMapper.map(customerEditDto,Existing_Customer);

        Existing_Customer= customerRepo.save(Existing_Customer);

        return customerEditDto;
    }
}
