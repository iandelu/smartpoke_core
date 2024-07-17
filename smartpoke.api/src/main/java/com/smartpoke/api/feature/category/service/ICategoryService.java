package com.smartpoke.api.feature.category.service;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.product.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> getAllCategories();

    Category saveCategory(String name, String emoji, String lan);

    List<CategoryDto> getCategoriesByType(String type);

}
