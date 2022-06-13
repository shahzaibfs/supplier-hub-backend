package com.fyp.supplierHub.order.repository;

import com.fyp.supplierHub.customer.entity.ShippingAddress;
import com.fyp.supplierHub.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Integer> {


    @Query("Select o from Orders o where o.customer.customer_id =?1 ")
    List<Orders> getTrackOrdersByCustomer (int id );

    @Query("Select sa from Orders sa Where sa.ordersId = ?1 "+
            "and sa.customer.customer_id = ?2 ")
    List<Orders> searchOrder(Integer ordersId , int customerId );
}
