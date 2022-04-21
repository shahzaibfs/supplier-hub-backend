package com.fyp.supplierHub.product.services;

import com.fyp.supplierHub.categories.Category;
import com.fyp.supplierHub.categories.CategoryService;
import com.fyp.supplierHub.exceptions.Exceptions.BadRequestException;
import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.product.Dtos.ProductDto;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.repository.ProductRepo;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.service.SupplierServiceImp;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSupplierService {
    private final ProductRepo productRepo ;
    private final SupplierServiceImp supplierServiceImp;
    private final CategoryService categoryService ;
    private final ModelMapper modelMapper ;

    @Autowired
    public ProductSupplierService(ProductRepo productRepo,
                                  SupplierServiceImp supplierServiceImp,
                                  CategoryService categoryService,
                                  ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.supplierServiceImp = supplierServiceImp;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper ;
    }

    public ProductDto saveOrEdit(String username  , ProductDto productDto){
        Supplier EXISTING_SUPPLIER =  supplierServiceImp.LoadAuthenticatedSupplier(username);
        Category Existing_Category = null;

        if(productDto.getCategory().getCategoryId() != null )
            Existing_Category = categoryService.loadById(productDto.getCategory().getCategoryId());
        else
            productDto.setCategory(null);

        //Todo: check if product is in our database
        if(productDto.getProductId() > 0){
            Product EXISTING_PRODUCT = productRepo.getProductByIdAndAuthenticatedUser(
                    EXISTING_SUPPLIER.getSupplierId(),
                    productDto.getProductId()
            ).orElseThrow(
                    ()-> new NotFoundException("Product not found ","/product-supplier/save-or-edit")
            );
            //Todo: check if category has children
            if(Existing_Category != null){
                if(Existing_Category.sizeOfCategories() <= 0 ) productDto.setCategory(Existing_Category) ;
                else productDto.setCategory(null);
            }


            modelMapper.map(productDto,EXISTING_PRODUCT);
            EXISTING_PRODUCT =  productRepo.save(EXISTING_PRODUCT);

            modelMapper.map(EXISTING_PRODUCT,productDto);

            return productDto ;

        }
        else{
            //Todo: check if category has children
            if(Existing_Category != null){
                if(Existing_Category.sizeOfCategories() <= 0 ) productDto.setCategory(Existing_Category) ;
                else productDto.setCategory(null);
            }

            Product New_Product = new Product();
            New_Product.setSupplier(EXISTING_SUPPLIER);

            modelMapper.map(productDto,New_Product);
            New_Product= productRepo.save(New_Product);

            productDto.setProductId(New_Product.getProductId());

            return productDto;
        }

    }
    public List<ProductDto> getAllSupplierProducts (String username ){
        Supplier Existing_Supplier  =  supplierServiceImp.LoadAuthenticatedSupplier(username);
        List<Product> Existing_Supplier_Products = productRepo.findAllBySupplierId(Existing_Supplier.getSupplierId());
        TypeToken<List<ProductDto>> typeToken = new TypeToken<>() {};

        List<ProductDto> productDtoList = modelMapper.map(Existing_Supplier_Products,typeToken.getType());

       return productDtoList;
    }
    @Transactional
    public int deleteOne (String username,Integer productId){
        Supplier EXISTING_SUPPLIER = supplierServiceImp.LoadAuthenticatedSupplier(username);
        System.out.println(productId+""+EXISTING_SUPPLIER.getSupplierId());
        int idDeleted  = productRepo.deleteProductById(EXISTING_SUPPLIER.getSupplierId(),productId);
        if(idDeleted>0){
            return idDeleted ;
        }else{
            throw new RuntimeException("product Not found and thus It's Not deleted ") ;
        }

    }
}
