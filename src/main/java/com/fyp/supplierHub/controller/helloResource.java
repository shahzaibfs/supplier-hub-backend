package com.fyp.supplierHub.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/home")
public class helloResource {

    @GetMapping
    String home(){
        return "i am home ";
    }
}
