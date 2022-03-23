package com.fyp.supplierHub.reposiory;

import com.fyp.supplierHub.entity.Supplier;
import com.fyp.supplierHub.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer> {

    @Query("select s from Supplier s where s.user.userName = ?1 " )
    Supplier findSupplierByUsername(String username);



}
