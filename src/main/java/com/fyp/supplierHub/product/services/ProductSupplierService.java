package com.fyp.supplierHub.product.services;

import com.fyp.supplierHub.categories.Category;
import com.fyp.supplierHub.categories.CategoryService;
import com.fyp.supplierHub.exceptions.Exceptions.BadRequestException;
import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.product.Dtos.ProductDto;
import com.fyp.supplierHub.product.Dtos.ProductOutOfStockReqDto;
import com.fyp.supplierHub.product.enitity.OutOfStock;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.repository.OutOfStockRepo;
import com.fyp.supplierHub.product.repository.ProductRepo;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.service.SupplierServiceImp;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSupplierService {
    private final ProductRepo productRepo ;
    private final OutOfStockRepo outOfStockRepo ;
    private final SupplierServiceImp supplierServiceImp;
    private final CategoryService categoryService ;

    private final ModelMapper modelMapper ;

    @Autowired
    public ProductSupplierService(ProductRepo productRepo,
                                  SupplierServiceImp supplierServiceImp,
                                  CategoryService categoryService,
                                  ModelMapper modelMapper,
                                  OutOfStockRepo outOfStockRepo) {
        this.productRepo = productRepo;
        this.supplierServiceImp = supplierServiceImp;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper ;
        this.outOfStockRepo = outOfStockRepo;
    }

    public ProductDto saveOrEdit(String username  , ProductDto productDto){
        Supplier EXISTING_SUPPLIER =  supplierServiceImp.LoadAuthenticatedSupplier(username);
        Category Existing_Category = null;

        if(productDto.getCategory().getCategoryId() != null )
            Existing_Category = categoryService.loadById(productDto.getCategory().getCategoryId());
        else
            productDto.setCategory(null);

        productDto.setDateOfCreation(LocalDate.now());
        productDto.setNewProduct(true);

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
    @Transactional
    public List<ProductDto> getAllSupplierProducts (String username ){
        Supplier Existing_Supplier  =  supplierServiceImp.LoadAuthenticatedSupplier(username);
        List<Product> Existing_Supplier_Products = productRepo
                .findAllWithSupplierIdAndOutOfStockNull(Existing_Supplier.getSupplierId());
        TypeToken<List<ProductDto>> typeToken = new TypeToken<>() {};

        List<ProductDto> productDtoList = modelMapper.map(Existing_Supplier_Products,typeToken.getType());

       return productDtoList;
    }
    @Transactional
    public int deleteOne (String username,Integer productId){
        Supplier EXISTING_SUPPLIER = supplierServiceImp.LoadAuthenticatedSupplier(username);
        int idDeleted  = productRepo.deleteProductById(EXISTING_SUPPLIER.getSupplierId(),productId);
        if(idDeleted>0){
            return idDeleted ;
        }else{
            throw new RuntimeException("product Not found and thus It's Not deleted ") ;
        }

    }

    public ProductDto updateToOutOfStockTable (String username , ProductOutOfStockReqDto productOutOfStockReqDto) {
        Supplier Existing_Supplier  =  supplierServiceImp . LoadAuthenticatedSupplier(username );
        Product Existing_Product  = productRepo
                .getProductByIdAndAuthenticatedUser(Existing_Supplier.getSupplierId()
                        ,productOutOfStockReqDto.getProductId())
                .orElseThrow(
                        ()-> new NotFoundException("Product Not Found PLease Provide Correct Product "
                                , "/api/v1.0/product-supplier/update-to-outOfStock"));
        ProductDto productDto  = new ProductDto();


        if(Existing_Product.getOutOfStock() != null)  throw new BadRequestException("Product is Already in Out fo Stock"
                , "/api/v1.0/product-supplier/update-to-outOfStock");

        // Todo : setup out of stock and assign to Existing product
        OutOfStock outOfStock = new OutOfStock(productOutOfStockReqDto.getOutOfStockDate());
        outOfStock = outOfStockRepo.save(outOfStock);

        Existing_Product.setOutOfStock(outOfStock);

        Existing_Product = productRepo.save(Existing_Product);
        modelMapper.map(Existing_Product,productDto);

        return productDto ;

    }

    public ProductDto removeFromOutOfStock (String username ,int productId) {
        Supplier Existing_Supplier  =  supplierServiceImp . LoadAuthenticatedSupplier(username );
        Product Existing_Product  = productRepo
                .getProductByIdAndAuthenticatedUser(Existing_Supplier.getSupplierId()
                        ,productId)
                .orElseThrow(
                        ()-> new NotFoundException("Product Not Found PLease Provide Correct Product "
                                , "/api/v1.0/product-supplier/update-to-outOfStock"));
        ProductDto productDto  = new ProductDto();

        if(Existing_Product.getOutOfStock() == null) throw new BadRequestException("Product is Already In The Stock"
                , "/api/v1.0/product-supplier/update-to-outOfStock");

        OutOfStock outOfStock  = Existing_Product.getOutOfStock();
        Existing_Product.setOutOfStock(null);

        Existing_Product = productRepo.save(Existing_Product);

        outOfStockRepo.delete(outOfStock);

        modelMapper.map(Existing_Product,productDto);

        return productDto ;



    }

    public List<ProductDto> getAllOutOfStockProducts(String username){
        Supplier Existing_Supplier = supplierServiceImp.LoadAuthenticatedSupplier(username);
        List<Product> products= productRepo.getAllSupplierProductsInOutOfStockTable(Existing_Supplier.getSupplierId());
        TypeToken<List<ProductDto>> typeToken = new TypeToken<List<ProductDto>>(){} ;
        List <ProductDto>  productDtoList = modelMapper.map(products,typeToken.getType());

        return productDtoList ;
    }


    }
