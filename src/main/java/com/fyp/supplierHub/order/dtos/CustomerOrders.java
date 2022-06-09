package com.fyp.supplierHub.order.dtos;


import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CustomerOrders {

    private Integer ordersId ;

    private String status ;

    private LocalDate dateOfCreation ;

    private Double totalPrice ;

    private List<CustomerEachOrder> orders ;

}
