package com.fyp.supplierHub.categories;

import java.util.List;


public interface CategoryService {


    List<CategoryDTO> loadAllCategories();
    Category loadById(int categoryId);

}
