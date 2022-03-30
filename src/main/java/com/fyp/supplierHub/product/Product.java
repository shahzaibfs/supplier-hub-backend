package com.fyp.supplierHub.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.supplier.entity.Supplier;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId ;

    @Column(name = "product_name")
    private String productName ;
    @Column(name = "product_price")
    private long productPrice;
    @Column(name = "product_desc")
    private String productDesc;
    @Column(name = "product_details")
    private String productDetails;
    @Column(name = "product_cover_url")
    private String productCoverUrl ;
    @Column(name = "product_weight")
    private String productWeight;

    private LocalDate dateOfCreation = LocalDate.now();
    @Column(name = "product_size")
    private int productSize;

    @Column(name = "product_min_order")
    private int productMinOrder ;

    @Column(name = "is_new_product")
    private boolean isNewProduct =false ;
    @Column(name = "out_of_stock")
    private boolean outOfStock =false ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id",referencedColumnName = "supplier_id")
    @JsonIgnore
    private Supplier supplier;

    public Product(int productId, String productName, long productPrice, String productDesc, String productDetails, String productCoverUrl, String productWeight, LocalDate dateOfCreation, int productSize, int productMinOrder, Supplier supplier) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productDetails = productDetails;
        this.productCoverUrl = productCoverUrl;
        this.productWeight = productWeight;
        this.dateOfCreation = dateOfCreation;
        this.productSize = productSize;
        this.productMinOrder = productMinOrder;
        this.supplier = supplier;
    }

    public Product() {
    }

    public Product(String productName, long productPrice, String productDesc, String productDetails, String productCoverUrl, String productWeight, LocalDate dateOfCreation, int productSize, int productMinOrder) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productDetails = productDetails;
        this.productCoverUrl = productCoverUrl;
        this.productWeight = productWeight;
        this.dateOfCreation = dateOfCreation;
        this.productSize = productSize;
        this.productMinOrder = productMinOrder;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public boolean isNewProduct() {
        return isNewProduct;
    }

    public void setNewProduct(boolean newProduct) {
        isNewProduct = newProduct;
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

}


