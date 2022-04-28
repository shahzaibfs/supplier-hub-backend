package com.fyp.supplierHub.customer.controller;

import com.fyp.supplierHub.customer.models.ShippingAddressDto;
import com.fyp.supplierHub.customer.service.CustomerShippingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1.0/customer-shipping-address")
public class CustomerShippingAddressController {

    private final CustomerShippingAddressService customerShippingAddressService ;
    private  final Logger logger ;

    public CustomerShippingAddressController(CustomerShippingAddressService customerShippingAddressService) {
        this.logger = LoggerFactory.getLogger(CustomerShippingAddressController.class);
        this.customerShippingAddressService = customerShippingAddressService;
    }

    @GetMapping
    private ResponseEntity<List<ShippingAddressDto>> findAll_ShippingAddress (Authentication authentication){
        logger.info("CustomerShippingAddressController -> FindAll :" + authentication.getName());
        String username  = authentication.getName();
        return ResponseEntity.ok(customerShippingAddressService.findAll_ShippingAddress(username));
    }
    @PostMapping
    private  ResponseEntity<ShippingAddressDto> createOrUpdate_shippingAddress (Authentication authentication ,@RequestBody ShippingAddressDto shippingAddressDto){
        String username = authentication.getName();
        return ResponseEntity.ok(customerShippingAddressService.createOrUpdate_shippingAddress(username,shippingAddressDto));
    }

    @DeleteMapping(value = "{shippingAddressId}")
    private ResponseEntity<String> delete_shippingAddress (Authentication authentication ,@PathVariable Integer shippingAddressId){
        String username  = authentication.getName();
        return ResponseEntity.ok(customerShippingAddressService.delete_shippingAddress(username,shippingAddressId));
    }
}
