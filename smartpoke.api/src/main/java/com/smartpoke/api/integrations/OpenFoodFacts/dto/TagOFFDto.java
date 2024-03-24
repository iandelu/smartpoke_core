package com.smartpoke.api.integrations.OpenFoodFacts.dto;

import com.smartpoke.api.feature.category.model.Tag;
import lombok.Data;

@Data
public class TagOFFDto {
    private String name;
    private String language;

    public TagOFFDto(String offTag) {
        tagToIngredient(offTag);
    }

    private void tagToIngredient(String message){
        String[] parts = message.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato de ingrediente inválido");
        }

        this.language = parts[0].trim();
        this.name = parts[1].trim();
    }

    public Tag toEntity(){
        Tag ingredient = new Tag();
        ingredient.setLan(this.language);
        ingredient.setName(this.name);

        return ingredient;
    }

}
