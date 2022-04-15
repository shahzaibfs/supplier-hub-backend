package com.fyp.supplierHub.categories;

import com.fyp.supplierHub.product.enitity.Product;


import java.util.List;
import java.util.Set;

public class CategoryDTO {

    private Integer categoryId;
    private Integer categoryParent;

    private String categoryName;
    private String categoryPhotoUrl;

    private List<CategoryDTO> categories ;

    public CategoryDTO() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryParent() {
        return categoryParent;
    }

    public void setCategoryParent(Integer categoryParent) {
        this.categoryParent = categoryParent;
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

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

}
