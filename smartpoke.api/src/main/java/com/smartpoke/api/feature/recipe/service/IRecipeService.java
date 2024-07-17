package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.feature.recipe.dto.RecipeDto;
import com.smartpoke.api.feature.recipe.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IRecipeService{

    RecipeDto createRecipe(Recipe recipe);
    RecipeDto findById(Long l);
    RecipeDto updateRecipe(Long id, Recipe recipe);
    void deleteRecipe(Long id);

    Recipe createRecipeFromUrl(String url);

    Recipe createRecipeFromUrl(String url, String wild);
    List<RecipeDto> createRecipeListFromUrl(List<String> urls);
    List<RecipeDto> loadRecipeBase();
    Page<RecipeDto> getAllRecipes(int page, int size);
    Page<RecipeDto> getRecipesByDifficult(int page, int size, String difficult);
    Page<RecipeDto> searchRecipes(int page, int size, String name);
    Page<RecipeDto> getRecipesByCategory(int page, int size, String category);
}
