package com.smartpoke.api.feature.category.dto;

import com.smartpoke.api.feature.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String lan;
    private String emoji;

    public Category toCategory() {
        Category category = new Category();
        category.setId(this.id);
        category.setName(this.name);
        category.setLan(this.lan);
        category.setEmoji(this.emoji);
        return category;
    }

    public static CategoryDto fromCategory(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getLan(), category.getEmoji());
    }
}
