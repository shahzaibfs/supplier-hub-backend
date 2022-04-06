package com.fyp.supplierHub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1.0/user")
public class UserController {

    private final MyUserDetailService myUserDetailService;

    @Autowired
    public UserController(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @PostMapping("/create")
    final ResponseEntity<?> createNewUser(@RequestBody UserRequest newUser)
    {
        return ResponseEntity.ok(myUserDetailService.createNewUser(newUser));
    }
}
