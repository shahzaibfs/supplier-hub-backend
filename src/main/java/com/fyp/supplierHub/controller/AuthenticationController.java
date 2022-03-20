package com.fyp.supplierHub.controller;

import com.fyp.supplierHub.models.AuthRequest;
import com.fyp.supplierHub.models.JwtTokenResponse;
import com.fyp.supplierHub.service.MyUserDetailService;
import com.fyp.supplierHub.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/authenticate")
public class AuthenticationController {

    static{
        System.out.println("ok running");
    }

    private final AuthenticationManager authenticationManager ;
    private final JwtTokenUtil jwtTokenUtil ;
    private final MyUserDetailService myUserDetailService;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager
            ,JwtTokenUtil jwtTokenUtil
            ,MyUserDetailService myUserDetailService
    )
    {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.myUserDetailService = myUserDetailService ;
    }

    /************* this route will check if user is authenticated or not if
     authenticated then return the valid jwt token with user Details  with response of 200  ***********/
    @PostMapping
    public ResponseEntity<?> generateJwtToken (@RequestBody AuthRequest authRequest) throws RuntimeException{
        System.out.println(authRequest.getUsername()+authRequest.getPassword());
        try{
            authenticationManager.authenticate
                    (
                            new UsernamePasswordAuthenticationToken
                                    (authRequest.getUsername(),authRequest.getPassword())
                    );
        }
        catch(BadCredentialsException e){
            throw new BadCredentialsException("incorrect Username and password ");
        }

        UserDetails EXISTING_USER = myUserDetailService.loadUserByUsername(authRequest.getUsername());

        final String  getToken=  jwtTokenUtil.generateToken(EXISTING_USER);
        final JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(getToken,myUserDetailService.getUserFromDatabase(EXISTING_USER.getUsername()));

        return ResponseEntity.ok(jwtTokenResponse);

    }
}
