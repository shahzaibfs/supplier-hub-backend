package com.fyp.supplierHub.supplier.service;

import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.models.PublicSupplierData;
import com.fyp.supplierHub.supplier.repository.SupplierRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PublicSupplierService {

    private SupplierRepo supplierRepo ;
    private ModelMapper modelMapper ;

    public PublicSupplierService(SupplierRepo supplierRepo, ModelMapper modelMapper) {
        this.supplierRepo = supplierRepo;
        this.modelMapper = modelMapper;
    }

    public PublicSupplierData getSupplier(Integer id){

        Supplier ExistingSupplier = supplierRepo.findById(id).orElseThrow(()->  new NotFoundException("Supplier Not found","api/v1.0/supplier-public/"+id));
        PublicSupplierData publicSupplierData = new PublicSupplierData();

        modelMapper.map(ExistingSupplier,publicSupplierData);


        return publicSupplierData;
    }
}
