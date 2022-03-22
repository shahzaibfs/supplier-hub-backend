package com.fyp.supplierHub.reposiory;

import com.fyp.supplierHub.entity.Supplier;
import com.fyp.supplierHub.entity.SupplierBrandAddresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SupplierBrandAddressRepo extends JpaRepository<SupplierBrandAddresses,Integer> {

    Collection<SupplierBrandAddresses> findAllBySupplier(Supplier s);

    @Query("select s from SupplierBrandAddresses s where s.supplier.supplierId = ?1 and s.supplierBrandAddressId = ?2" )
    SupplierBrandAddresses findBrandAddressByUsernameAndId(Integer supplierId , Integer brandAddressId);

    @Modifying
    @Query("Delete from SupplierBrandAddresses s where s.supplier.supplierId = ?1 and s.supplierBrandAddressId = ?2" )
    void deleteBrandAddress(Integer supplierId , Integer brandAddressId);



}
