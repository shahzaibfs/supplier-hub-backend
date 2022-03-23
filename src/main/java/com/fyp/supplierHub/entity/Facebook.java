package com.fyp.supplierHub.entity;

import javax.persistence.*;

@Entity
@Table(name = "supplier_facebook")
public class Facebook {

    @Id
    @Column(name = "facebook_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facebookId;
    @Column (name = "facebook_url")
    private String facebookUrl;




    public Facebook(int facebookId, String facebookUrl) {
        this.facebookId = facebookId;
        this.facebookUrl = facebookUrl;
    }

    public Facebook(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public Facebook() {
    }

    public int getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(int facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }
}
