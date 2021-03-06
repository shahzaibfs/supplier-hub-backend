package com.fyp.supplierHub.product.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.cart.entity.CartProducts;
import com.fyp.supplierHub.categories.Category;
import com.fyp.supplierHub.supplier.entity.Supplier;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id",referencedColumnName = "supplier_id")
    @JsonIgnore
    private Supplier supplier;


    @ManyToOne
    @JoinColumn(name = "categ_id",referencedColumnName = "category_id")
    private Category category ;

    @OneToOne
    @JoinColumn(name="out_of_stock_id",referencedColumnName = "out_of_stock_id")
    private OutOfStock outOfStock ;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartProducts> cartProductsList ;

    public List<CartProducts> getCartProductsList() {
        return cartProductsList;
    }

    public void setCartProductsList(List<CartProducts> cartProductsList) {
        this.cartProductsList = cartProductsList;
    }

    public OutOfStock getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(OutOfStock outOfStock) {
        this.outOfStock = outOfStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(
            int productId,
            String productName,
            long productPrice,
            String productDesc,
            String productDetails
            , String productCoverUrl,
            String productWeight,
            LocalDate dateOfCreation,
            int productSize,
            int productMinOrder,
            Supplier supplier) {
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





}


