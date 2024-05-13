package com.smartpoke.api.feature.category.service;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService
{


    @Autowired
    CategoryRepository categoryRepository;



    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(String name, String emoji, String lan) {
        return categoryRepository.findByName(name)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(name);
                    newCategory.setEmoji(emoji);
                    newCategory.setLan(lan);
                    return categoryRepository.save(newCategory);
                });
    }
}
