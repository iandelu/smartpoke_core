package com.smartpoke.api.feature.category.service;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.category.repository.CategoryRepository;
import com.smartpoke.api.feature.product.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService
{


    @Autowired
    CategoryRepository categoryRepository;



    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).toList();
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

    @Override
    public List<CategoryDto> getCategoriesByType(String type) {
        List<Category> categories =  switch (type) {
            case "product" -> categoryRepository.findCategoriesInUseProduct();
            case "recipe" -> categoryRepository.findCategoriesInUseByRecipes();
            default -> categoryRepository.findAll();
        };
        return categories.stream().map(CategoryDto::new).toList();
    }
}
