package com.fyp.supplierHub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId ;

    @Column(name = "user_name" ,nullable = false)
    private String userName;
    @Column(name = "user_email",nullable = false)
    private String userEmail;
    @Column(name = "user_password",nullable = false)
    @JsonIgnore
    private String userPassword ;
    @Column(name = "account_creation_date",nullable = false)
    private Date accountCreationDate ;
    @Column(name = "account_status")
    private boolean accountStatus;
    @Column(name = "is_account_ban")
    private boolean isAccountBan ;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns =  @JoinColumn(name = "user_id",nullable = false) ,
            inverseJoinColumns = @JoinColumn(name = "role_id",nullable = false)

    )
    private Set<Role> roles ;

    public User(int userId, String userName, String userEmail, String userPassword, Date accountCreationDate, boolean accountStatus, boolean isAccountBan, Set<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.accountCreationDate = accountCreationDate;
        this.accountStatus = accountStatus;
        this.isAccountBan = isAccountBan;
        this.roles = roles;
    }

    public User(String userName, String userEmail, String userPassword, Date accountCreationDate, boolean accountStatus, boolean isAccountBan) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.accountCreationDate = accountCreationDate;
        this.accountStatus = accountStatus;
        this.isAccountBan = isAccountBan;
    }

    public User(String userName, String userEmail, String userPassword, Date accountCreationDate, boolean accountStatus, boolean isAccountBan, Set<Role> roles) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.accountCreationDate = accountCreationDate;
        this.accountStatus = accountStatus;
        this.isAccountBan = isAccountBan;
        this.roles = roles;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(Date accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isAccountBan() {
        return isAccountBan;
    }

    public void setAccountBan(boolean accountBan) {
        isAccountBan = accountBan;
    }

    public Collection<SimpleGrantedAuthority> getRoles() {
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        this.roles.forEach(role -> {
          roles.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
