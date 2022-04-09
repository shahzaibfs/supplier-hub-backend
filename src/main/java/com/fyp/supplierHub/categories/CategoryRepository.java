package com.fyp.supplierHub.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


    @Override
    @Query( "SELECT c FROM Category c WHERE c.categoryParent=NULL")
    List<Category> findAll() ;

}
