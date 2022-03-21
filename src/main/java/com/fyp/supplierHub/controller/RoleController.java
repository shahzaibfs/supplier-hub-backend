package com.fyp.supplierHub.controller;

import com.fyp.supplierHub.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/roles")
public class RoleController {

    private final RoleService roleService ;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    final ResponseEntity<?> loadAllRoles(){
        return ResponseEntity.ok(roleService.loadAllRoles());
    }
}
