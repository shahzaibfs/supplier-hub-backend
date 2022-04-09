package com.fyp.supplierHub.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService
{
    private final CategoryRepository categoryRepository ;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> loadAllCategories() {

        return categoryRepository.findAll();
    }
}
