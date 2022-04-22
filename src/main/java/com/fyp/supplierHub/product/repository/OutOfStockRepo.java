package com.fyp.supplierHub.product.repository;

import com.fyp.supplierHub.product.enitity.OutOfStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutOfStockRepo  extends JpaRepository<OutOfStock,Integer> {
}
