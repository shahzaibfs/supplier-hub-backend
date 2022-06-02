package com.fyp.supplierHub.order.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrderResponse {
    private  Integer orderId ;
    private String status ;
    private LocalDate dateOfCreation ;
    private Double totalPrice ;
}
