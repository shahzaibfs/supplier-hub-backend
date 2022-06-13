package com.fyp.supplierHub.customer.models;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ShippingAddressSearchQuery {

    private String ShopName;
    private String shippingAddress;

}
