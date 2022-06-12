package com.fyp.supplierHub.order.repository;

import com.fyp.supplierHub.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

    @Query("SELECT o FROM Order o WHERE o.supplier.supplierId = ?1 and o.status = 'pending'")
    List<Order> getPendingOrdersBysupplier (Integer id);

    @Query("SELECT o FROM Order o WHERE o.supplier.supplierId = ?1 and o.status = 'active'")
    List<Order> getActiveOrdersBySupplier (Integer id);

    @Query("SELECT o FROM Order o WHERE o.supplier.supplierId = ?1 and o.status = 'delivered'")
    List<Order> getDeliveredOrders (Integer id);

    @Query("SELECT o FROM Order o WHERE o.supplier.supplierId = ?1 and o.orderId =?2")
    Order getOrderById (Integer supplierId,Integer orderId);

    @Query("SELECT o FROM Order o WHERE o.supplier.supplierId = ?1 and o.viewStatus =?2")
    Page<Order> findByViewStatus(Integer supplierId, String viewStatus, Pageable page);

    @Query("SELECT o FROM Order o WHERE o.supplier.supplierId = ?1 and o.viewStatus =?2")
    List<Order> fidAllByViewStatus(Integer supplierId, String viewStatus);

}
