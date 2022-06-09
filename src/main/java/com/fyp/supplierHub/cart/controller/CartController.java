package com.fyp.supplierHub.cart.controller;

import com.fyp.supplierHub.cart.dtos.CartReq;
import com.fyp.supplierHub.cart.entity.Cart;
import com.fyp.supplierHub.cart.service.CartService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1.0/cart")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;

    @PostMapping
    private ResponseEntity<?> addProductsToCart (@Autowired Authentication authentication, @RequestBody List<CartReq> cartReqList){
        String customer_name = authentication.getName();
        return ResponseEntity.ok(cartService.addProductToCart(cartReqList,customer_name));
    }

    @GetMapping
    private ResponseEntity<Cart> getCart(Authentication authentication){
        return ResponseEntity.ok(cartService.getCart(authentication.getName()));
    }

}
