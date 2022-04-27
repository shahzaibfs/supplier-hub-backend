package com.fyp.supplierHub.customer.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer_shipping_addresses")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_addressId")
    private Integer id;
    private Integer postalCode;

    @Column (name = "shop_supervisor")
    private String shopSupervisor ;
    private String shopName;
    private String shippingAddress;
    private String city;

    @Column (name = "shop_supervisor_phNo")
    private String shopSupervisorPhNo;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    private Customer customer ;

    public ShippingAddress() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
