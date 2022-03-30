package com.fyp.supplierHub.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.product.Product;
import com.fyp.supplierHub.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId ;
    @Column(name = "supplier_firstname")
    private String supplierFirstname;
    @Column(name = "supplier_lastname")
    private String supplierLastname;
    @Column(name = "supplier_profile_url")
    private String supplierProfileUrl ;
    @Column(name = "supplier_bio")
    private String supplierBio;
    @Column(name = "supplier_contact_no")
    private long supplierContactNo;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "brand_owner_name")
    private String brandOwnerName;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    @JsonIgnore
    private User user ;
    @OneToMany(mappedBy = "supplier" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<SupplierBrandAddresses> supplierBrandAddresses ;

    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinColumn(
            name = "instagram_id"
            ,referencedColumnName = "instagram_id"
    )
    private Instagram instagram ;

    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinColumn(
            name = "facebook_id",
            referencedColumnName = "facebook_id"
    )
    private Facebook facebook ;

    @OneToMany( mappedBy = "supplier",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Product> products ;

    public Supplier(int supplierId, String supplierFirstname, String supplierLastname, String supplierProfileUrl, String supplierBio, long supplierContactNo, String brandName, String brandOwnerName, User user, Set<SupplierBrandAddresses> supplierBrandAddresses, Instagram instagram, Facebook facebook) {
        this.supplierId = supplierId;
        this.supplierFirstname = supplierFirstname;
        this.supplierLastname = supplierLastname;
        this.supplierProfileUrl = supplierProfileUrl;
        this.supplierBio = supplierBio;
        this.supplierContactNo = supplierContactNo;
        this.brandName = brandName;
        this.brandOwnerName = brandOwnerName;
        this.user = user;
        this.supplierBrandAddresses = supplierBrandAddresses;
        this.instagram = instagram;
        this.facebook = facebook;
    }

    public Supplier() {
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierFirstname() {
        return supplierFirstname;
    }

    public void setSupplierFirstname(String supplierFirstname) {
        this.supplierFirstname = supplierFirstname;
    }

    public String getSupplierLastname() {
        return supplierLastname;
    }

    public void setSupplierLastname(String supplierLastname) {
        this.supplierLastname = supplierLastname;
    }

    public String getSupplierProfileUrl() {
        return supplierProfileUrl;
    }

    public void setSupplierProfileUrl(String supplierProfileUrl) {
        this.supplierProfileUrl = supplierProfileUrl;
    }

    public String getSupplierBio() {
        return supplierBio;
    }

    public void setSupplierBio(String supplierBio) {
        this.supplierBio = supplierBio;
    }

    public long getSupplierContactNo() {
        return supplierContactNo;
    }

    public void setSupplierContactNo(long supplierContactNo) {
        this.supplierContactNo = supplierContactNo;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<SupplierBrandAddresses> getSupplierBrandAddresses() {
        return supplierBrandAddresses;
    }

    public void setSupplierBrandAddresses(Set<SupplierBrandAddresses> supplierBrandAddresses) {
        this.supplierBrandAddresses = supplierBrandAddresses;
    }

    public Instagram getInstagram() {
        return instagram;
    }

    public void setInstagram(Instagram instagram) {
        this.instagram = instagram;
    }

    public Facebook getFacebook() {
        return facebook;
    }

    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
