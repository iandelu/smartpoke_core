package com.smartpoke.api.integrations.OpenFoodFacts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartpoke.api.feature.product.dto.ProductMacronutrientsDto;
import com.smartpoke.api.feature.product.model.ProductNutrients;
import lombok.Data;

@Data
public class ProductMacronutrientsOFFDto implements ProductMacronutrientsDto {
    @JsonProperty("energy-kcal_100g")
    private Integer energeticKcal;
    @JsonProperty("carbohydrates_100g")
    private Double cabs;
    @JsonProperty("fat_100g")
    private Double fats;
    @JsonProperty("saturated-fat_100g")
    private Double saturatedFats;
    @JsonProperty("fiber_100g")
    private Double fibre;
    @JsonProperty("proteins_100g")
    private Double protein;
    @JsonProperty("sugars_100g")
    private Double sugars;
    @JsonProperty("salt_100g")
    private Double salt;
    @JsonProperty("sodium_100g")
    private Double sodium;

    @Override
    public ProductNutrients toEntity() {
       ProductNutrients entity = new ProductNutrients();
       entity.setCalories(this.energeticKcal);
       entity.setCabs(this.cabs);
       entity.setFats(this.fats);
       entity.setFibre(this.fibre);
       entity.setProtein(this.protein);
       entity.setSalt(this.salt);
       entity.setSaturatedFats(this.saturatedFats);
       entity.setSodium(this.sodium);
       return entity;
    }
}