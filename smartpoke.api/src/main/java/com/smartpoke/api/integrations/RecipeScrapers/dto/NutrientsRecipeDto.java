package com.smartpoke.api.integrations.RecipeScrapers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartpoke.api.common.utils.NumberExtractor;
import com.smartpoke.api.feature.recipe.model.NutrientsRecipe;
import lombok.Data;

@Data
public class NutrientsRecipeDto {
    @JsonProperty("calories")
    private String energeticKcal;
    @JsonProperty("carbohydrateContent")
    private String carbohydrates100g;
    @JsonProperty("cholesterolContent")
    private String cholesterolContent;
    @JsonProperty("fatContent")
    private String fat100g;
    @JsonProperty("fiberContent")
    private String fiber100g;
    @JsonProperty("proteinContent")
    private String proteins100g;
    @JsonProperty("saturatedFatContent")
    private String saturatedFatContent;
    @JsonProperty("servingSize")
    private String servingSize;
    @JsonProperty("sodiumContent")
    private String sodiumContent;
    @JsonProperty("sugarContent")
    private String sugarContent;

    public NutrientsRecipe toEntity() {
        NutrientsRecipe nutrients = new NutrientsRecipe();
        nutrients.setCalories(NumberExtractor.getInt(energeticKcal, 0));
        nutrients.setCabs(NumberExtractor.getDouble(carbohydrates100g, 0.0));
        nutrients.setCholesterol(NumberExtractor.getDouble(cholesterolContent, 0.0));
        nutrients.setFats(NumberExtractor.getDouble(fat100g, 0.0));
        nutrients.setFiber(NumberExtractor.getDouble(fiber100g, 0.0));
        nutrients.setProtein(NumberExtractor.getDouble(proteins100g, 0.0));
        nutrients.setSaturatedFats(NumberExtractor.getDouble(saturatedFatContent, 0.0));
        nutrients.setAmount(NumberExtractor.getInt(servingSize, 1));
        nutrients.setSalt(NumberExtractor.getDouble(sodiumContent, 0.0));
        nutrients.setSugar(NumberExtractor.getDouble(sugarContent, 0.0));

        return nutrients;
    }
}
