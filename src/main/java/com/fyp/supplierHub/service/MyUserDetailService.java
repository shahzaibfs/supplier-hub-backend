package com.fyp.supplierHub.service;

import com.fyp.supplierHub.entity.Role;
import com.fyp.supplierHub.entity.User;
import com.fyp.supplierHub.models.UserRequest;
import com.fyp.supplierHub.reposiory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;
    @Autowired
    private PasswordEncoder encoder ;


    @Autowired
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;

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

    public Collection<SimpleGrantedAuthority> getAuthorities(Set<Role> allRoles) {
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

    public final User createNewUser(UserRequest newUser){
        User CREATE_USER = new User();
        CREATE_USER.setUserName(newUser.getUsername());
        CREATE_USER.setUserEmail(newUser.getEmail());
        CREATE_USER.setUserPassword(encoder.encode(newUser.getPassword()));
        CREATE_USER.setAccountCreationDate(LocalDate.now());
        CREATE_USER.setRoles(new HashSet<Role>(Arrays.asList(newUser.getRole())));
        System.out.println(newUser.getRole());
        return userRepo.save(CREATE_USER);
    }

}
