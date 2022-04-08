package com.fyp.supplierHub.user;

import com.fyp.supplierHub.user.dtos.UserEditDto;
import com.fyp.supplierHub.user.dtos.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public  ResponseEntity<?> createNewUser(@RequestBody UserRequest newUser)
    {
        return ResponseEntity.ok(myUserDetailService.createNewUser(newUser));
    }
    @PostMapping("/edit")
    private ResponseEntity<?> editExistingUser(Authentication authentication, @RequestBody UserEditDto userEditDto){
        return ResponseEntity.ok(myUserDetailService.editUser(authentication.getName(),userEditDto));
    }

    @GetMapping
    private ResponseEntity<?> authenticatedUser(Authentication authentication) {
        return ResponseEntity.ok(myUserDetailService.getUserFromDatabase(authentication.getName()));
    }

}
