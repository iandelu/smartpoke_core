package com.smartpoke.api.integrations.RecipeScrapers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartpoke.api.common.utils.NumberExtractor;
import com.smartpoke.api.feature.recipe.model.NutrientsRecipe;
import lombok.Data;

@Data
public class NutrientsRecipeDto {
    @JsonProperty("calories")
    private String energeticKcal;
//    @JsonProperty("carbohydrates_100g")
//    private Double cabs;
//    @JsonProperty("fat_100g")
//    private Double fats;
//    @JsonProperty("fiber_100g")
//    private Double fibre;
//    @JsonProperty("proteins_100g")
//    private Double protein;
//    @JsonProperty("salt_100g")
//    private Double salt;

    public NutrientsRecipe toEntity(){
        NutrientsRecipe nutrients = new NutrientsRecipe();
        nutrients.setCalories(NumberExtractor.getIntPosition(energeticKcal,1));


        return nutrients;
    }
}
