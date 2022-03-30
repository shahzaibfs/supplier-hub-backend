package com.fyp.supplierHub.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImp implements RoleService{

    private final RoleRepo roleRepo ;

    @Autowired
    public RoleServiceImp(RoleRepo roleRepo)
    {
        this.roleRepo = roleRepo;
    }

    @Override
    public  Collection<Role> loadAllRoles(){
        return roleRepo.findAll();
    }
}
