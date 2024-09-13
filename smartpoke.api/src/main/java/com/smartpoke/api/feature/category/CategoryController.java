package com.smartpoke.api.feature.category;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.category.service.CategoryService;
import com.smartpoke.api.feature.product.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{type}")
    public ResponseEntity<List<CategoryDto>> getCategories(@PathVariable(name = "type", required = false) String type) {
        return ResponseEntity.ok().body(categoryService.getCategoriesByType(type));
    }


}
