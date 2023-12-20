package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.feature.recipe.dto.RecipeDto;
import com.smartpoke.api.feature.recipe.model.Recipe;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface IRecipeService{

    RecipeDto createRecipe(Recipe recipe);
    Page<RecipeDto> getAllRecipes(int page, int size);
    RecipeDto findById(Long l);
    RecipeDto updateRecipe(Long id, Recipe recipe);
    void deleteRecipe(Long id);
    Recipe createRecipeFromUrl(String url, String wild);
    List<Recipe> createRecipeListFromUrl(List<String> urls);
    List<RecipeDto> loadRecipeBase();
}
