package com.fyp.supplierHub.product.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.categories.Category;
import com.fyp.supplierHub.product.enitity.OutOfStock;
import com.fyp.supplierHub.supplier.entity.Supplier;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class ProductDto {
    private int productId ;
    private int productSize;
    private int productMinOrder ;
    private long productPrice;

    private String productName ;
    private String productDesc;
    private String productDetails;
    private String productCoverUrl ;
    private String productWeight;

    private LocalDate dateOfCreation ;

    private boolean isNewProduct ;


    private Category category ;
    private OutOfStock outOfStock ;

    public ProductDto() {
    }

    public int getProductId() {
        return productId;
    }


    public OutOfStock getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(OutOfStock outOfStock) {
        this.outOfStock = outOfStock;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }

    public int getProductMinOrder() {
        return productMinOrder;
    }

    public void setProductMinOrder(int productMinOrder) {
        this.productMinOrder = productMinOrder;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductCoverUrl() {
        return productCoverUrl;
    }

    public void setProductCoverUrl(String productCoverUrl) {
        this.productCoverUrl = productCoverUrl;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public boolean isNewProduct() {
        return isNewProduct;
    }

    public void setNewProduct(boolean newProduct) {
        isNewProduct = newProduct;
    }




    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
