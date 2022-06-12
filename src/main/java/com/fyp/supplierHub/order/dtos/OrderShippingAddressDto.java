package com.fyp.supplierHub.order.dtos;


import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrderShippingAddressDto {

    private Integer postalCode;

    private String shopSupervisor ;
    private String shopName;
    private String shippingAddress;
    private String city;
    private String shopSupervisorPhNo;


}
