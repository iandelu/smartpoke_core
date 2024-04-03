package com.smartpoke.api.feature.product.dto;

import com.smartpoke.api.feature.category.model.Category;
import lombok.Data;

@Data
public class CategoryDto {

    private String name;
    private String lan;
    private String emoji;

    public CategoryDto(Category category) {
        this.name = category.getName();
        this.lan = category.getLan();
        this.emoji = category.getEmoji();
    }
    public Category toEntity() {
        Category category = new Category();
        category.setName(this.name);
        category.setLan(this.lan);
        category.setEmoji(this.emoji);
        return category;
    }

}
