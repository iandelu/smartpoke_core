package com.smartpoke.api.feature.recipe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class NutrientsRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private Integer calories;
    private Double fats;
    private Double saturatedFats;
    private Double cholesterol;
    private Double cabs;
    private Double fiber;
    private Double protein;
    private Double salt;
    private Double sugar;
}
