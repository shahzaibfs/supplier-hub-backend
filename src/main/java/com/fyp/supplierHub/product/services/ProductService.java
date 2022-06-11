package com.fyp.supplierHub.product.services;

import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.product.Dtos.PUBLIC_ProductDTO;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.repository.ProductRepo;
import com.fyp.supplierHub.supplier.service.SupplierServiceImp;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ModelMapper modelMapper;

    private final ProductRepo productRepo ;
    private final SupplierServiceImp supplierServiceImp;


    @Autowired
    public ProductService(
            ProductRepo productRepo,
            SupplierServiceImp supplierServiceImp,
            ModelMapper modelMapper
            )
    {
        this.productRepo = productRepo;
        this.supplierServiceImp = supplierServiceImp;
        this.modelMapper = modelMapper;
    }

    public Collection<PUBLIC_ProductDTO> loadAll (){
        Collection<Product> products = productRepo.findAll();
        TypeToken<List<PUBLIC_ProductDTO>> typeToken = new TypeToken<>(){};



        List<PUBLIC_ProductDTO>  PUBLIC_ProductDTOS = modelMapper.map(products,typeToken.getType());
        return PUBLIC_ProductDTOS ;

    }



    public PUBLIC_ProductDTO loadOne (Integer id){
        Product EXISTING_PRODUCT = productRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("ID Not Found!","api/v1.0/product/"+id));

        PUBLIC_ProductDTO public_productDTO = new PUBLIC_ProductDTO() ;
        modelMapper.map(EXISTING_PRODUCT ,public_productDTO);

        return  public_productDTO ;
    }


    public List<PUBLIC_ProductDTO> loadAll_newProducts() {
        List<Product> products= productRepo.findAllByIsNewProduct(true) ;
        TypeToken<List<PUBLIC_ProductDTO>> typeToken = new TypeToken<>(){};


        List<PUBLIC_ProductDTO>  PUBLIC_ProductDTOS = modelMapper.map(products,typeToken.getType());
        return PUBLIC_ProductDTOS ;
    }

    public Page<PUBLIC_ProductDTO> searchProduct (String query){
        Pageable page = PageRequest.of(0, 5);

        Page<Product> products = productRepo.searchProducts(query,page);
        TypeToken<Page<PUBLIC_ProductDTO>> typeToken = new TypeToken<>(){};

        Page<PUBLIC_ProductDTO> public_productDTOS = modelMapper.map(products,typeToken.getType());

        return public_productDTOS ;

    }
}
