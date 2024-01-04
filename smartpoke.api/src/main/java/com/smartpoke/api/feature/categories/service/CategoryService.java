package com.smartpoke.api.feature.categories.service;

import com.smartpoke.api.feature.categories.model.Category;
import com.smartpoke.api.feature.categories.repository.CategoryRepository;
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
}
