package com.fyp.supplierHub.user.dtos;

public class UserEditDto {

    private String username;
    private String email;
    private String password ;
    private String oldPassword ;

    public UserEditDto(String username, String email, String password, String oldPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.oldPassword = oldPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
