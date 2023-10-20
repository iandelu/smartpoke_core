package com.smartpoke.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductsMacronutrientsDto {
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
    private Double sal;

}