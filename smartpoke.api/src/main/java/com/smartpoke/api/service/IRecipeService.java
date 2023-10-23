package com.smartpoke.api.service;

import com.smartpoke.api.model.recipes.Recipe;
import com.smartpoke.api.model.users.User;

import java.util.Set;

public interface IRecipeService{

    Recipe createRecipe(Recipe recipe);
    Set<Recipe> getAllRecipes();
    Recipe findById(Long l);
    Recipe updateRecipe(Long id, Recipe recipe);


    void deleteRecipe(Long id);
}
