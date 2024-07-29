package com.ecommerce.project.service;

import com.ecommerce.project.Reposioty.CategoryRepository;
import com.ecommerce.project.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService{

   // private List<Category> categories = new ArrayList<>();
    @Autowired
    private CategoryRepository categoryRepository;
    private Long nextId=1L;
    @Override

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));

        categoryRepository.delete(category);
        return "Category with Given Id: "+ categoryId +" has been deleted Successfully !!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).
                orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;


    }

}
