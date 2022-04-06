package com.fyp.supplierHub.user;

import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.repository.CustomerRepo;
import com.fyp.supplierHub.exceptions.Exceptions.BadRequestException;
import com.fyp.supplierHub.exceptions.Exceptions.DatabaseException;
import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.exceptions.Exceptions.UniqueColumnException;
import com.fyp.supplierHub.roles.Role;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.repository.SupplierRepo;
import com.sun.istack.NotNull;
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
    private final CustomerRepo customerRepo ;
    private final PasswordEncoder encoder ;



    @Autowired
    public MyUserDetailService(UserRepo userRepo ,SupplierRepo supplierRepo, PasswordEncoder encoder ,CustomerRepo customerRepo)
    {
        this.userRepo = userRepo;
        this.supplierRepo=supplierRepo;
        this.encoder=encoder;
        this.customerRepo =customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {

            User EXISTING_USER = getUserFromDatabase(username);
            Collection<SimpleGrantedAuthority> EXISTING_USER_AUTHORITIES =
                    getAuthorities(EXISTING_USER.getRoles());

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

    private Collection<SimpleGrantedAuthority> getAuthorities(Set<Role> allRoles)
    {
        Collection <SimpleGrantedAuthority> roles = new ArrayList<>();
        allRoles.forEach(role -> {
            roles.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return roles;
    }

    public  User getUserFromDatabase(String username) throws UsernameNotFoundException
    {

        Optional<User> EXISTING_USER = userRepo.findByUserName(username);
        EXISTING_USER.orElseThrow( ()-> new NotFoundException("INVALID Credentials","Please provide the Correct Username and Password") );

        return EXISTING_USER.get();

    }


    @Transactional
    public  String  createNewUser(UserRequest userRequest)
    {
            if(userRequest.getUsername().isEmpty() || userRequest.getRole() ==null ||
                userRequest.getEmail().isEmpty()|| userRequest.getPassword().isEmpty())
            {
                throw new BadRequestException("Null Fields" ,"api/v1.0/user/create");
            }

            int EXISTING_USER = userRepo.findByUsername(userRequest.getUsername());
            if(EXISTING_USER >0)
            {
                throw new UniqueColumnException("username Unique","api/v1.0/user/create");
            }

            User NEW_USER = new User();
            NEW_USER.setUserName(userRequest.getUsername());
            NEW_USER.setUserEmail(userRequest.getEmail());
            NEW_USER.setUserPassword(encoder.encode(userRequest.getPassword()));
            NEW_USER.setAccountCreationDate(LocalDate.now());
            NEW_USER.setRoles(new HashSet<Role>(Arrays.asList(userRequest.getRole())));

            try
            {
                userRepo.save(NEW_USER);
            }
            catch (Exception e)
            {
                throw new DatabaseException("failed to Create","api/v1.0/user/create");
            }
            switch (userRequest.getRole().getRoleName())
            {
                case "CUSTOMER":
                    Customer NEW_CUSTOMER = new Customer();
                    NEW_CUSTOMER.setUser(NEW_USER);
                    customerRepo.save(NEW_CUSTOMER) ;
                    return NEW_USER.getUserName();

                case "SUPPLIER":

                    Supplier NEW_SUPPLIER = new Supplier();
                    NEW_SUPPLIER.setUser(NEW_USER);
                    supplierRepo.save(NEW_SUPPLIER) ;
                    return NEW_USER.getUserName();

                default:
                    throw new DatabaseException("failed to Create","api/v1.0/user/create");

            }


    }

}
