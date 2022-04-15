package com.fyp.supplierHub.categories;

import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    @Override
    public Category loadById(int categoryId) {
        Category Existing_Category =  categoryRepository.findById(categoryId).orElseThrow(
                ()->new NotFoundException("Category Not found " , "/product-supplier/save-or-edit")
        );
        return Existing_Category;
    }
}
