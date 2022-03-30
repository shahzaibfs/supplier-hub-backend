package com.fyp.supplierHub.jwtToken;

import com.fyp.supplierHub.user.User;

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
