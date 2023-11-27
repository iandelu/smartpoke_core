package com.smartpoke.api.feature.recipe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class NutrientsRecipe {
    @Id
    private String idRecipe;
    private Integer amount;
    private Integer calories;
    private Double fats;
    private Double cabs;
    private Double fibre;
    private Double protein;
    private Double salt;
}
