package com.fyp.supplierHub.models;

import org.springframework.security.core.userdetails.UserDetails;

public class JwtTokenResponse {
    private final String token ;
    private final UserDetails userDetails;

    public JwtTokenResponse(String token, UserDetails userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
