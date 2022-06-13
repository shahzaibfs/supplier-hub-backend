package com.fyp.supplierHub.order.entity;


import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.supplier.entity.Supplier;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "each_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private  Integer orderId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private String status ;

    @Column(name = "view_status")
    private String viewStatus= "waiting";

    @ManyToOne
    @JoinColumn(
            name = "supplier_id",
            referencedColumnName = "supplier_id"
    )
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "product_id"
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "orders_id",
            referencedColumnName = "orders_id"
    )
    private Orders orders;

    @PrePersist
    void preInsert() {
        if (this.viewStatus == null)
            this.viewStatus = "waiting";
    }
}
