package com.fyp.supplierHub.categories;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService
{
    private final CategoryRepository categoryRepository ;
    private final ModelMapper modelMapper ;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository,
                              ModelMapper modelMapper)
    {
        this.categoryRepository = categoryRepository;
        this.modelMapper=modelMapper;
    }


    @Override
    public List<CategoryDTO> loadAllCategories() {

        List<Category> categories = categoryRepository.findAllWhereCategIsNull();
        List<CategoryDTO> categoryDTOList =  modelMapper
                .map(categories, new TypeToken<List<CategoryDTO>>(){}.getType());

        return  categoryDTOList;
    }
}
