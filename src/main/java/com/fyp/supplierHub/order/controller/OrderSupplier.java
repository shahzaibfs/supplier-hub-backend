package com.fyp.supplierHub.order.controller;

import com.fyp.supplierHub.order.dtos.OrderSupplierDto;
import com.fyp.supplierHub.order.entity.Order;
import com.fyp.supplierHub.order.service.OrderSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/set-active-order/{orderId}")
    private ResponseEntity<?>  setActiveOrder(@Autowired Authentication authentication , @PathVariable Integer orderId){
        String username  =authentication.getName();
        return ResponseEntity.ok(orderSupplierService.SetActiveOrder(username,orderId));
    }

    @PutMapping("/set-reject-order/{orderId}")
    private ResponseEntity<?>  setRejectedOrder(@Autowired Authentication authentication , @PathVariable Integer orderId){
        String username  =authentication.getName();
        return ResponseEntity.ok(orderSupplierService.setRejectOrder(username,orderId));
    }

    @PutMapping("/set-delivered-order/{orderId}")
    private ResponseEntity<?>  setDeliveredOrder(@Autowired Authentication authentication , @PathVariable Integer orderId){
        String username  =authentication.getName();
        return ResponseEntity.ok(orderSupplierService.setDeliveredOrder(username,orderId));
    }

    @GetMapping("/notificaton-order")
    private ResponseEntity<?> getNotificationOrders (@Autowired Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(orderSupplierService.getNotificationOrders(username));
    }

    @GetMapping("/set-Notification-order")
    private ResponseEntity<String> setNotfiicationOrder (@Autowired Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(orderSupplierService.setNotificationOrder(username));
    }
}
