package com.fyp.supplierHub.service;

import com.fyp.supplierHub.entity.User;
import com.fyp.supplierHub.reposiory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User EXISTING_USER = getUserFromDatabase(username);

        UserDetails user = new org.springframework.security.core.userdetails.User
                (
                        EXISTING_USER.getUserName(),
                        EXISTING_USER.getUserPassword(),
                        true,
                        true,
                        true,
                        !EXISTING_USER.isAccountBan(),
                        EXISTING_USER.getRoles()
                );

        return  user;
    }

    public final User getUserFromDatabase(String username) throws UsernameNotFoundException{

        Optional<User> EXISTING_USER = userRepo.findByUserName(username);
        EXISTING_USER.orElseThrow( ()-> new UsernameNotFoundException("InValid Username && Password") );
        System.out.println("hy");
        return EXISTING_USER.get();
    }

}
