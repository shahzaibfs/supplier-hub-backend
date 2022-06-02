package com.fyp.supplierHub.order.controller;

import com.fyp.supplierHub.order.dtos.OrderRequest;
import com.fyp.supplierHub.order.dtos.OrderResponse;
import com.fyp.supplierHub.order.dtos.PublicProductDetails;
import com.fyp.supplierHub.order.service.OrderService;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

}
