package com.fyp.supplierHub.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.product.enitity.Product;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "cart_products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Integer cartProductId ;

    @Column(name = "quantity")
    private Integer quantity ;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "cart_id")
    @JsonIgnore
    private Cart cart;
}
