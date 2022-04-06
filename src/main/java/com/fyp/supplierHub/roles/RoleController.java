package com.fyp.supplierHub.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1.0/roles")
public class RoleController {

    private final RoleService roleServiceImp;

    @Autowired
    public RoleController(RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping
    final ResponseEntity<?> loadAllRoles(){
        return ResponseEntity.ok(roleServiceImp.loadAllRoles());
    }
}
