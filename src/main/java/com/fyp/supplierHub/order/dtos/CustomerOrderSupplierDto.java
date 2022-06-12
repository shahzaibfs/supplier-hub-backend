package com.fyp.supplierHub.order.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CustomerOrderSupplierDto {
    private int customer_id;
    private String customerName;
    private String customerContactNo;

}
