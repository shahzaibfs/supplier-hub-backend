package com.fyp.supplierHub.supplier.models;

import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.entity.Facebook;
import com.fyp.supplierHub.supplier.entity.Instagram;

public class SupplierRequest {

    private String firstName ;
    private String lastName ;
    private String profileUrl;
    private String bio;
    private long contactNo;
    private String brandName ;
    private String brandOwnerName ;
    private SupplierBrandAddresses brandAddress ;
    private Facebook facebook ;
    private Instagram instagram ;



    public Facebook getFacebook() {
        return facebook;
    }

    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    public Instagram getInstagram() {
        return instagram;
    }

    public void setInstagram(Instagram instagram) {
        this.instagram = instagram;
    }

    public SupplierRequest(String firstName, String lastName, String profileUrl, String bio, long contactNo, String brandName, String brandOwnerName, SupplierBrandAddresses brandAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileUrl = profileUrl;
        this.bio = bio;
        this.contactNo = contactNo;
        this.brandName = brandName;
        this.brandOwnerName = brandOwnerName;
        this.brandAddress = brandAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandOwnerName() {
        return brandOwnerName;
    }

    public void setBrandOwnerName(String brandOwnerName) {
        this.brandOwnerName = brandOwnerName;
    }

    public SupplierBrandAddresses getBrandAddress() {
        return brandAddress;
    }

    public void setBrandAddress(SupplierBrandAddresses brandAddress) {
        this.brandAddress = brandAddress;
    }
}
