package com.fyp.supplierHub.models;

public class BrandAddressRequest {
    private int id ;
    private  String city;
    private String address ;
    private String name ;
    private Long postalCode ;

    public BrandAddressRequest(String city, String address, String name, Long postalCode) {
        this.city = city;
        this.address = address;
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
