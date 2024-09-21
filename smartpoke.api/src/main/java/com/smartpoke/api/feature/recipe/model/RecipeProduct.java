package com.smartpoke.api.feature.recipe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.smartpoke.api.feature.product.model.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecipeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @Column(columnDefinition = "text")
    private String text;
    @Column(columnDefinition = "text")
    private String ingredientName;

    @ManyToOne
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;
}