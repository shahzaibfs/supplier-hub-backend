package com.fyp.supplierHub.models;

import com.fyp.supplierHub.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtTokenResponse {
    private final String token ;
    private final User userDetails;


    public JwtTokenResponse(String token, User userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public User getUserDetails() {
        return userDetails;
    }

}
