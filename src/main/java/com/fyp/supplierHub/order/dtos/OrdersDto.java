package com.fyp.supplierHub.order.dtos;


import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.entity.ShippingAddress;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrdersDto {
    private Integer ordersId ;
    private LocalDate dateOfCreation ;
    private CustomerOrderSupplierDto customer ;
    private OrderShippingAddressDto shippingAddress;


}
