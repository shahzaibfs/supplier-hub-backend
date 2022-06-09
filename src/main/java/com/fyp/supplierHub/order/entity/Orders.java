package com.fyp.supplierHub.order.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.entity.ShippingAddress;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Integer ordersId ;

    @Column(name = "status")
    private String status ;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation ;

    private Double totalPrice ;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customer_id"
    )
    private Customer customer ;

    @ManyToOne
    @JoinColumn(
            name = "customer_shipping_address_id",
            referencedColumnName = "shipping_addressId"
    )
    private ShippingAddress shippingAddress;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "orders")
    @JsonIgnore
    private List<Order> orders ;

}
