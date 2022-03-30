package com.fyp.supplierHub.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.supplier.entity.Supplier;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "supplier_brand_addresses")
public class SupplierBrandAddresses {

    @Id
    @Column(name = "supplier_brand_address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierBrandAddressId ;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city ;
    @Column(name = "postal_code")
    private long postalCode ;
    @Column(name = "is_main")
    private boolean isMain ;
    @Column(name = "creation_date")
    private LocalDate creationDate = LocalDate.now() ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "supplier_id",
            referencedColumnName = "supplier_id"
    )
    @JsonIgnore
    private Supplier supplier ;

    public SupplierBrandAddresses(int supplierBrandAddressId, String address, String city, long postalCode, boolean isMain, LocalDate creationDate, Supplier supplier) {
        this.supplierBrandAddressId = supplierBrandAddressId;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.isMain = isMain;
        this.creationDate = creationDate;
        this.supplier = supplier;
    }

    public SupplierBrandAddresses() {
    }

    public int getSupplierBrandAddressId() {
        return supplierBrandAddressId;
    }

    public void setSupplierBrandAddressId(int supplierBrandAddressId) {
        this.supplierBrandAddressId = supplierBrandAddressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(long postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
