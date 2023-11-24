package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.feature.recipe.model.Recipe;

import java.util.Set;

public interface IRecipeService{

    Recipe createRecipe(Recipe recipe);
    Set<Recipe> getAllRecipes();
    Recipe findById(Long l);
    Recipe updateRecipe(Long id, Recipe recipe);


    void deleteRecipe(Long id);
    Recipe createRecipeFromUrl(String url);
}
