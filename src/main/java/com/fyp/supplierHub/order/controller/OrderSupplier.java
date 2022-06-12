package com.fyp.supplierHub.order.controller;

import com.fyp.supplierHub.order.dtos.OrderSupplierDto;
import com.fyp.supplierHub.order.service.OrderSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1.0/order-supplier")
@RequiredArgsConstructor
public class OrderSupplier {

    private final OrderSupplierService orderSupplierService ;

    @GetMapping("/pending-orders")
    private ResponseEntity<List<OrderSupplierDto>> getPendingOrders (@Autowired Authentication authentication){
        String username  = authentication.getName();
        return ResponseEntity.ok(orderSupplierService.getPendingOrders(username));
    }
    @GetMapping("/active-orders")
    private ResponseEntity<List<OrderSupplierDto>> getActiveOrders (@Autowired Authentication authentication){
        String username  = authentication.getName();
        return ResponseEntity.ok(orderSupplierService.getActiveOrders(username));
    }

    @GetMapping("/delivered-orders")
    private ResponseEntity<List<OrderSupplierDto>> getDeliveredOrders (@Autowired Authentication authentication){
        String username  = authentication.getName();
        return ResponseEntity.ok(orderSupplierService.getDeliveredOrders(username));
    }

}
