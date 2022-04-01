package com.fyp.supplierHub.customer.entity;

import com.fyp.supplierHub.user.User;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_contact_no")
    private String customerContactNo;

    @Column(name = "customer_profile_url")
    private String customer_profile_url;

    @Column(name = "shop_address")
    private String shopAddress;

    @Column(name = "shop_owner_name")
    private String shopOwnerName;

    @Column(name = "shop_name")
    private String shopName;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false
    )
    private User user ;


    public Customer() {
    }

    public Customer(int customer_id, String customerName, String customerAddress, String customerContactNo, String customer_profile_url, String shopAddress, String shopOwnerName, String shopName) {
        this.customer_id = customer_id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContactNo = customerContactNo;
        this.customer_profile_url = customer_profile_url;
        this.shopAddress = shopAddress;
        this.shopOwnerName = shopOwnerName;
        this.shopName = shopName;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContactNo() {
        return customerContactNo;
    }

    public void setCustomerContactNo(String customerContactNo) {
        this.customerContactNo = customerContactNo;
    }

    public String getCustomer_profile_url() {
        return customer_profile_url;
    }

    public void setCustomer_profile_url(String customer_profile_url) {
        this.customer_profile_url = customer_profile_url;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopOwnerName() {
        return shopOwnerName;
    }

    public void setShopOwnerName(String shopOwnerName) {
        this.shopOwnerName = shopOwnerName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
