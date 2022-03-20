package com.fyp.supplierHub.controller;

import com.fyp.supplierHub.entity.User;
import com.fyp.supplierHub.models.UserRequest;
import com.fyp.supplierHub.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/user")
public class UserController {

    private final MyUserDetailService myUserDetailService;

    @Autowired
    public UserController(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @PostMapping("/create")
    final ResponseEntity<?> createNewUser(@RequestBody UserRequest newUser){
        return ResponseEntity.ok(myUserDetailService.createNewUser(newUser));
    }
}
