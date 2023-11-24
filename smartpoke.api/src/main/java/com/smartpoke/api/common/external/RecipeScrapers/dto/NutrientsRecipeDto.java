package com.smartpoke.api.common.external.RecipeScrapers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
