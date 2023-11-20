package com.smartpoke.api.external.OpenFoodFacts;

import com.smartpoke.api.model.products.Ingredient;
import lombok.Data;

@Data
public class IngredientOFFDto {
    private String name;

    public IngredientOFFDto(String offTag) {
        tagToIngredient(offTag);
    }

    private void tagToIngredient(String message){
        name=message;
    }

    public Ingredient toEntity(){
        Ingredient ingredient = new Ingredient();

        return ingredient;
    }

}
