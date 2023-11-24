package com.smartpoke.api.common.utils;

import com.smartpoke.api.feature.product.model.Ingredient;
import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.recipe.model.RecipeIngredients;

import java.math.BigDecimal;

public class IngredientParser {
    public RecipeIngredients parse(String ingredientLine, Recipe recipe) {
        String[] parts = ingredientLine.split(" ", 2);
        BigDecimal amount = new BigDecimal(parts[0]);
        String ingredientName = parts[1];

        Ingredient ingredient = findOrCreateIngredient(ingredientName);

        RecipeIngredients recipeIngredient = new RecipeIngredients();
        recipeIngredient.setAmount(amount);

        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setRecipe(recipe);

        return recipeIngredient;
    }

    private Ingredient findOrCreateIngredient(String name) {

        return new Ingredient(); // Stub
    }
}
