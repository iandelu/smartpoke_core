package com.smartpoke.api.integrations.OpenFoodFacts.dto;

import com.smartpoke.api.feature.product.model.Ingredient;
import lombok.Data;

@Data
public class IngredientOFFDto {
    private String name;
    private String language;

    public IngredientOFFDto(String offTag) {
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

    public Ingredient toEntity(){
        Ingredient ingredient = new Ingredient();
        ingredient.setLanguage(this.language);
        ingredient.setName(this.name);

        return ingredient;
    }

}