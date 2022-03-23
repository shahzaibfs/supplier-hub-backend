package com.fyp.supplierHub.service;

import com.fyp.supplierHub.entity.Role;
import com.fyp.supplierHub.entity.Supplier;
import com.fyp.supplierHub.entity.User;
import com.fyp.supplierHub.models.UserRequest;
import com.fyp.supplierHub.reposiory.SupplierRepo;
import com.fyp.supplierHub.reposiory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;
    private final SupplierRepo supplierRepo ;
    @Autowired
    private PasswordEncoder encoder ;


    @Autowired
    public MyUserDetailService(UserRepo userRepo ,SupplierRepo supplierRepo) {
        this.userRepo = userRepo;
this.supplierRepo=supplierRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User EXISTING_USER = getUserFromDatabase(username);
        Collection<SimpleGrantedAuthority> EXISTING_USER_AUTHORITIES = getAuthorities(EXISTING_USER.getRoles());

        UserDetails user = new org.springframework.security.core.userdetails.User
                (
                        EXISTING_USER.getUserName(),
                        EXISTING_USER.getUserPassword(),
                        true,
                        true,
                        true,
                        !EXISTING_USER.isAccountBan(),
                        EXISTING_USER_AUTHORITIES

                );

        return  user;
    }

    private final  Collection<SimpleGrantedAuthority> getAuthorities(Set<Role> allRoles) {
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
       allRoles.forEach(role -> {
            roles.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return roles;
    }

    public final User getUserFromDatabase(String username) throws UsernameNotFoundException{

        Optional<User> EXISTING_USER = userRepo.findByUserName(username);
        EXISTING_USER.orElseThrow( ()-> new UsernameNotFoundException("InValid Username && Password") );
        System.out.println("hy");
        return EXISTING_USER.get();
    }


    public final String  createNewUser(UserRequest userRequest){
            User NEW_USER = new User();

            NEW_USER.setUserName(userRequest.getUsername());
            NEW_USER.setUserEmail(userRequest.getEmail());
            NEW_USER.setUserPassword(encoder.encode(userRequest.getPassword()));
            NEW_USER.setAccountCreationDate(LocalDate.now());
        System.out.println(userRequest.getRole());
            NEW_USER.setRoles(new HashSet<Role>(Arrays.asList(userRequest.getRole())));

            userRepo.save(NEW_USER);
            switch (userRequest.getRole().getRoleName()){
                case "CUSTOMER":
                    return null ;
                case "SUPPLIER":
                    Supplier NEW_SUPPLIER = new Supplier();
                    NEW_SUPPLIER.setUser(NEW_USER);
                    supplierRepo.save(NEW_SUPPLIER) ;
                    return "User created Succesfully ";
                default:
                    return "Error While Creating User ";

            }


    }

}
