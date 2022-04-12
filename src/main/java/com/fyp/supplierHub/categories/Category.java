package com.fyp.supplierHub.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp.supplierHub.product.Product;
import com.fyp.supplierHub.supplier.entity.Supplier;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_photo_url")
    private String categoryPhotoUrl;

    @Column(name = "category_parent"
    )
    private Integer categoryParent;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(
            name = "category_parent",
            referencedColumnName = "category_id",
            insertable = false
    )
    private List<Category> categories ;


    @OneToMany(mappedBy = "category" ,fetch = FetchType.LAZY)
    private Set<Product> products ;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts
            (Set<Product> products) {
        this.products = products;
    }

    public Integer getCategoryParent() {
        return categoryParent;
    }

    public void setCategoryParent(Integer categoryParent) {
        this.categoryParent = categoryParent;
    }

    public Category(int categoryId, String categoryName, String categoryPhotoUrl, List<Category> categories) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryPhotoUrl = categoryPhotoUrl;
        this.categories = categories;
    }

    public Category() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPhotoUrl() {
        return categoryPhotoUrl;
    }

    public void setCategoryPhotoUrl(String categoryPhotoUrl) {
        this.categoryPhotoUrl = categoryPhotoUrl;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
