package com.fyp.supplierHub.supplier.repository;

import com.fyp.supplierHub.customer.entity.ShippingAddress;
import com.fyp.supplierHub.supplier.entity.SupplierBrandAddresses;
import com.fyp.supplierHub.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SupplierBrandAddressRepo extends JpaRepository<SupplierBrandAddresses,Integer> {

    Collection<SupplierBrandAddresses> findAllBySupplier(Supplier s);

    @Query("select s from SupplierBrandAddresses s where s.supplier.supplierId = ?1 and s.supplierBrandAddressId = ?2" )
    SupplierBrandAddresses findBrandAddressByUsernameAndId(Integer supplierId , Integer brandAddressId);

    @Query("select s from SupplierBrandAddresses s where s.supplier.supplierId = ?1 and s.isMain = ?2" )
    SupplierBrandAddresses findMainAddress(Integer supplierId , boolean isMain);

    @Modifying
    @Query("Delete from SupplierBrandAddresses s where s.supplier.supplierId = ?1 and s.supplierBrandAddressId = ?2" )
    void deleteBrandAddress(Integer supplierId , Integer brandAddressId);


    @Query("Select sa from SupplierBrandAddresses sa Where sa.supplier.supplierId= :supplierId "+
            " and sa.city LIKE CONCAT('%', :city , '%') " )
    List<SupplierBrandAddresses> searchSupplierAddresses(int supplierId , String city);

}
