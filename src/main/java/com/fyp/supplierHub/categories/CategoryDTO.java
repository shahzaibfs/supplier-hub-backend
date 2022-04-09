package com.fyp.supplierHub.categories;

import java.util.List;


public class CategoryDTO {

    private Integer id ;
    private String name ;
    private String photoUrl;
    private Integer parent ;
    private List<Category> categories;

    public CategoryDTO(Integer id, String name, String photoUrl, Integer parent, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.parent = parent;
        this.categories = categories;
    }

    public CategoryDTO() {
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
