package com.fyp.supplierHub.reposiory;

import com.fyp.supplierHub.entity.Supplier;
import com.fyp.supplierHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer> {
    Supplier findSupplierByUser(User user);

}
