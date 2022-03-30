package com.fyp.supplierHub.supplier.repository;

import com.fyp.supplierHub.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer> {

    @Query("select s from Supplier s where s.user.userName = ?1 " )
    Supplier findSupplierByUsername(String username);



}
