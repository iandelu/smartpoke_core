package com.smartpoke.api.feature.recipe.model;

import com.smartpoke.api.feature.product.model.Ingredient;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @Column(columnDefinition = "text")
    private String ingredientText;

    @ManyToOne(cascade = CascadeType.ALL)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

}