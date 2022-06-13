package com.fyp.supplierHub.order.controller;

import com.fyp.supplierHub.order.dtos.*;
import com.fyp.supplierHub.order.service.OrderService;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1.0/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService ;

    @PostMapping
    private ResponseEntity<OrderResponse> postOrder (Authentication authentication, @RequestBody
    OrderRequest orderRequest){
        String customer_name  = authentication.getName();
        return ResponseEntity.ok(orderService.postOrder(customer_name,orderRequest));
    }

    @GetMapping
    private ResponseEntity<?> getOrdersByCustomer (@Autowired Authentication authentication){

        return  ResponseEntity.ok(orderService.getAllOrders(authentication.getName()));
    }


    @GetMapping("/{orderId}")
    private ResponseEntity<List<CustomerOrders>> searchOrders (@Autowired Authentication authentication , @PathVariable Integer orderId){
        return ResponseEntity.ok(orderService.getOrdersByOrderId(authentication.getName(),orderId));
    }

}
