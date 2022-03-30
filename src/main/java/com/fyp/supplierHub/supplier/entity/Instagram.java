package com.fyp.supplierHub.supplier.entity;

import javax.persistence.*;

@Entity
@Table(name = "supplier_instagram")
public class Instagram {

    @Id
    @Column(name = "instagram_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instagramId ;

    @Column (name = "instagram_url")
    private String instagram_url ;

    public Instagram(int instagramId, String instagram_url) {
        this.instagramId = instagramId;
        this.instagram_url = instagram_url;
    }

    public Instagram() {
    }

    public Instagram(String instagram_url) {

        this.instagram_url = instagram_url;
    }

    public int getInstagramId() {
        return instagramId;
    }

    public void setInstagramId(int instagramId) {
        this.instagramId = instagramId;
    }

    public String getInstagram_url() {
        return instagram_url;
    }

    public void setInstagram_url(String instagram_url) {
        this.instagram_url = instagram_url;
    }
}
