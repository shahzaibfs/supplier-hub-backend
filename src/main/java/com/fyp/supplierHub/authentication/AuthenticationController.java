package com.fyp.supplierHub.authentication;

import com.fyp.supplierHub.exceptions.Exceptions.BadRequestException;
import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.jwtToken.JwtTokenResponse;
import com.fyp.supplierHub.jwtToken.JwtTokenUtil;
import com.fyp.supplierHub.supplier.service.SupplierService;
import com.fyp.supplierHub.user.User;


import com.fyp.supplierHub.user.MyUserDetailService;
import com.fyp.supplierHub.supplier.service.SupplierServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/authenticate")
public class AuthenticationController {

    static{
        System.out.println("ok running");
    }

    private final AuthenticationManager authenticationManager ;
    private final JwtTokenUtil jwtTokenUtil ;
    private final MyUserDetailService myUserDetailService;
    private final SupplierService supplierServiceImp;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager
            , JwtTokenUtil jwtTokenUtil
            , MyUserDetailService myUserDetailService
            , SupplierServiceImp supplierServiceImp
    )
    {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.myUserDetailService = myUserDetailService ;
        this.supplierServiceImp = supplierServiceImp;
    }

    /************* this route will check if user is authenticated or not if
     authenticated then return the valid jwt token with user Details  with response of 200  ***********/
    @PostMapping
    public ResponseEntity<?> generateJwtToken (@RequestBody AuthRequest authRequest) throws RuntimeException
    {
        if(authRequest.getUsername().isEmpty() || authRequest.getPassword().isEmpty()){
            throw new BadRequestException("INVALID Credentials", "api/v1.0/authenticate");
        }
        try{
            authenticationManager.authenticate
                    (
                            new UsernamePasswordAuthenticationToken
                                    (authRequest.getUsername(),authRequest.getPassword())
                    );
        }
        catch(BadCredentialsException e){
           throw new NotFoundException("INVALID Credentials", "Please provide the Correct Username and password");
        }

        UserDetails EXISTING_USER = myUserDetailService.loadUserByUsername(authRequest.getUsername());

        final String  getToken=  jwtTokenUtil.generateToken(EXISTING_USER);
        final User user  = myUserDetailService.getUserFromDatabase(EXISTING_USER.getUsername());
        final JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(getToken,user);

        return ResponseEntity.ok(jwtTokenResponse);





    }



}
