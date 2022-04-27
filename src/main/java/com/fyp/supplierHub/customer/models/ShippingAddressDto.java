package com.fyp.supplierHub.customer.models;



public class ShippingAddressDto {
    private Integer id;
    private Integer postalCode;

    private String shopSupervisor ;
    private String shopName;
    private String shippingAddress;
    private String city;
    private String shopSupervisorPhNo;

    public ShippingAddressDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getShopSupervisor() {
        return shopSupervisor;
    }

    public void setShopSupervisor(String shopSupervisor) {
        this.shopSupervisor = shopSupervisor;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShopSupervisorPhNo() {
        return shopSupervisorPhNo;
    }

    public void setShopSupervisorPhNo(String shopSupervisorPhNo) {
        this.shopSupervisorPhNo = shopSupervisorPhNo;
    }
}
