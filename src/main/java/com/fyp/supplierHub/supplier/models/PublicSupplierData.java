package com.fyp.supplierHub.supplier.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.supplier.entity.Facebook;
import com.fyp.supplierHub.supplier.entity.Instagram;
import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PublicSupplierData {

    private int supplierId ;
    private String supplierFirstname;
    private String supplierLastname;
    private String supplierProfileUrl ;
    private String supplierBio;
    private String supplierContactNo;
    private String brandName;
    private String brandOwnerName;

    private Set<SupplierBrandAddresses> supplierBrandAddresses ;

    private Instagram instagram ;
    private Facebook facebook ;

    private Set<PublicSupplierProduct> products ;

}
