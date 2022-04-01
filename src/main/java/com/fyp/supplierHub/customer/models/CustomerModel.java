package com.fyp.supplierHub.customer.models;

import javax.persistence.Column;

public class CustomerModel {

    private int customer_id;
    private String customerName;
    private String customerAddress;
    private String customerContactNo;
    private String customer_profile_url;
    private String shopAddress;
    private String shopOwnerName;
    private String shopName;

    public CustomerModel(int customer_id, String customerName, String customerAddress, String customerContactNo, String customer_profile_url, String shopAddress, String shopOwnerName, String shopName) {
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
}
