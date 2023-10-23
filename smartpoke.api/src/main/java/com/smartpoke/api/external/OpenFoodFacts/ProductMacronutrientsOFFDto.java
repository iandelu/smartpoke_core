package com.smartpoke.api.external.OpenFoodFacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartpoke.api.dto.ProductMacronutrientsDto;
import com.smartpoke.api.model.products.ProductMacronutrients;
import lombok.Data;

@Data
public class ProductMacronutrientsOFFDto implements ProductMacronutrientsDto {
    @JsonProperty("energy-kcal_100g")
    private Integer energeticKcal;
    @JsonProperty("carbohydrates_100g")
    private Double cabs;
    @JsonProperty("fat_100g")
    private Double fats;
    @JsonProperty("fiber_100g")
    private Double fibre;
    @JsonProperty("proteins_100g")
    private Double protein;
    @JsonProperty("salt_100g")
    private Double salt;

    @Override
    public ProductMacronutrients toEntity() {
       ProductMacronutrients entity = new ProductMacronutrients();
       entity.setCalories(this.energeticKcal);
       entity.setCabs(this.cabs);
       entity.setFats(this.fats);
       entity.setFibre(this.fibre);
       entity.setProtein(this.protein);
       entity.setSalt(this.salt);
       return entity;
    }
}