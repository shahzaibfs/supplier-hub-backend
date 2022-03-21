package com.fyp.supplierHub.models;

import com.fyp.supplierHub.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtTokenResponse {
    private final String token ;
    private final User userDetails;
    private final Object userData;

    public JwtTokenResponse(String token, User userDetails,Object userData) {
        this.token = token;
        this.userDetails = userDetails;
        this.userData=userData;
    }

    public String getToken() {
        return token;
    }

    public User getUserDetails() {
        return userDetails;
    }

    public Object getUserData() {
        return userData;
    }
}
