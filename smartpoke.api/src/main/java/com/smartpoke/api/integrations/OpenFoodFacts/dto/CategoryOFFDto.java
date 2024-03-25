package com.smartpoke.api.integrations.OpenFoodFacts.dto;

import com.smartpoke.api.feature.category.model.Category;

public class CategoryOFFDto {
    private String name;
    private String lan;

    public CategoryOFFDto(String offCategory) {
        textToCategory(offCategory);
    }

    private void textToCategory(String message){
        String[] parts = message.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato de ingrediente inválido");
        }

        this.lan = parts[0].trim();
        this.name = parts[1].trim();
    }

    public Category toEntity(){
        Category category = new Category();
        category.setLan(this.lan);
        category.setName(this.name);

        return category;
    }
}
