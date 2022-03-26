package com.fyp.supplierHub.service;

import com.fyp.supplierHub.entity.Role;
import com.fyp.supplierHub.reposiory.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

    private final RoleRepo roleRepo ;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public  Collection<Role> loadAllRoles(){
        return roleRepo.findAll();
    }
}
