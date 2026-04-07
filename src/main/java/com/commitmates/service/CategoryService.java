package com.commitmates.service;

import com.commitmates.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public void saveCategory(Category category) {
        categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}