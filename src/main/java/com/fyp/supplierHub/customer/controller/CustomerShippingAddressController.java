package com.fyp.supplierHub.customer.controller;

import com.fyp.supplierHub.customer.entity.ShippingAddress;
import com.fyp.supplierHub.customer.models.ShippingAddressDto;
import com.fyp.supplierHub.customer.service.CustomerShippingAddressService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1.0/customer-shipping-address")
public class CustomerShippingAddressController {

    private final CustomerShippingAddressService customerShippingAddressService ;
    private  final Log logger ;

    public CustomerShippingAddressController(CustomerShippingAddressService customerShippingAddressService) {
        this.logger = LogFactory.getLog(CustomerShippingAddressController.class);
        this.customerShippingAddressService = customerShippingAddressService;
    }

    @GetMapping
    private ResponseEntity<List<ShippingAddressDto>> findAll (Authentication authentication){
        logger.info("CustomerShippingAddressController -> FindAll :" + authentication.getName());
        String username  = authentication.getName();
        return ResponseEntity.ok(customerShippingAddressService.findAll(username));
    }
    @PostMapping
    private final ResponseEntity<ShippingAddressDto> createOrUpdate (Authentication authentication ,@RequestBody ShippingAddressDto shippingAddressDto){
        String username = authentication.getName();
        return ResponseEntity.ok(customerShippingAddressService.createOrUpdate(username,shippingAddressDto));
    }
}
