package com.smartpoke.api.feature.product.dto;

import com.smartpoke.api.feature.category.model.Category;
import lombok.Data;

@Data
public class CategoryDto {

    private String name;
    private String lan;
    private String emoji;

    public Category toEntity() {
        Category category = new Category();
        category.setName(this.name);
        category.setLan(this.lan);
        category.setEmoji(this.emoji);
        return category;
    }

    public static CategoryDto fromEntity(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setLan(category.getLan());
        categoryDto.setEmoji(category.getEmoji());
        return categoryDto;
    }


}
