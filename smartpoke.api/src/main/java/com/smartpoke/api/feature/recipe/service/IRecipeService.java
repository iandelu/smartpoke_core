package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.feature.recipe.dto.RecipeDto;
import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
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
    List<RecipeDto> createRecipeListFromUrl(List<String> urls);
    List<RecipeDto> loadRecipeBase();
    Page<RecipeDto> filterRecipes(String name, Integer rating, DifficultyEnum difficulty, Integer time, Set<String> categories, int page, int size);
    Page<RecipeDto> searchRecipes(int page, int size, String name);
}
