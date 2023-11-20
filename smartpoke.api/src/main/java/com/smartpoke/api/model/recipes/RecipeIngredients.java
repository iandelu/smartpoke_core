package com.smartpoke.api.model.recipes;
import com.smartpoke.api.model.products.Ingredient;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class RecipeIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ingredient_lan", referencedColumnName = "lan"),
            @JoinColumn(name = "ingredient_name", referencedColumnName = "name")
    })
    private Ingredient ingredient;

}