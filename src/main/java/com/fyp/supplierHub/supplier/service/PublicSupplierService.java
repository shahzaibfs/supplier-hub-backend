package com.fyp.supplierHub.supplier.service;

import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.product.Dtos.PUBLIC_ProductDTO;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.models.PublicSupplierData;
import com.fyp.supplierHub.supplier.repository.SupplierRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PublicSupplierData> TopRatedSuppliers() {
        Page<Supplier> suppliers = supplierRepo.findAll(Pageable.ofSize(6));
        TypeToken<Page<PUBLIC_ProductDTO>> typeToken = new TypeToken<>(){};

        Page<PublicSupplierData> topRatedSuppliers= modelMapper.map(suppliers,typeToken.getType());
        return topRatedSuppliers;
    }
}
