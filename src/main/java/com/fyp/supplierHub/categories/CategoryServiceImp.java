package com.fyp.supplierHub.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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

        return categoryRepository.findAllWhereCategIsNull();
    }
}
