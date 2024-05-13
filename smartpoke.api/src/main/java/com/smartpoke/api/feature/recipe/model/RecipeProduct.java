package com.smartpoke.api.feature.recipe.model;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}