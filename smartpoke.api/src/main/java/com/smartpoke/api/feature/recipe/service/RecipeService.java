package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.external.RecipeScrapers.RecipeScraperClient;
import com.smartpoke.api.common.external.RecipeScrapers.dto.RecipeScrapDto;
import com.smartpoke.api.common.utils.NumberExtractor;
import com.smartpoke.api.feature.product.model.Ingredient;
import com.smartpoke.api.feature.product.repository.IngredientRepository;
import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.recipe.model.RecipeIngredient;
import com.smartpoke.api.feature.recipe.repository.RecipeIngredientsRepository;
import com.smartpoke.api.feature.recipe.repository.RecipeRepository;
import com.smartpoke.api.feature.recipe.repository.RecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class RecipeService implements IRecipeService{
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeIngredientsRepository recipeIngredientsRepository;
    @Autowired
    private RecipeStepRepository recipeStepRepository;
    @Autowired
    private RecipeScraperClient recipeScraperClient;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));
    }

    @Override
    public Recipe updateRecipe(Long id, Recipe recipe) {
        if (recipeRepository.existsById(id)) {
            recipe.setId(id);
            return recipeRepository.save(recipe);
        } else {
            return null;
        }
    }

    @Override
    public void deleteRecipe(Long id){recipeRepository.deleteById(id);}

    @Override
    public Recipe createRecipeFromUrl(String url, String wild) {
        try {
            RecipeScrapDto recipeScrapDto = recipeScraperClient.getRecipeScraped(url, wild);
            Recipe recipe = recipeScrapDto.toEntity();
            recipe.setRecipeIngredients(convertIngredients(recipeScrapDto.getIngredients()));

            return recipeRepository.save(recipe);
        } catch (IOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public Recipe createRecipeFromUrl(String url) {
        return createRecipeFromUrl(url, "true");
    }

    @Override
    public List<Recipe> createRecipeListFromUrl(List<String> urls) {
        List<Recipe> recipeListEntity = new ArrayList<>();
        urls.forEach( url -> {
            Recipe newRecipe = createRecipeFromUrl(url);
            recipeListEntity.add(newRecipe);
        });
        return recipeRepository.saveAll(recipeListEntity);
    }

    private Set<RecipeIngredient> convertIngredients(List<String> recipeIngredientsText) {
        Set<RecipeIngredient> recipeIngredients = new HashSet<>();
        if(recipeIngredientsText != null && !recipeIngredientsText.isEmpty()){
            for (String s : recipeIngredientsText) {
                recipeIngredients.add(textToIngredient(s));
            }
        }
        return recipeIngredients;
    }

    private RecipeIngredient textToIngredient(String text) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientText(text);
        recipeIngredient.setAmount(NumberExtractor.getDoublePosition(text, 0));

        String ingredientText = "";
        Ingredient ingredient = ingredientRepository.findByName(ingredientText).orElse(createNewIngredient(ingredientText));

        recipeIngredient.setIngredient(ingredient);
        return recipeIngredient;
    }

    private Ingredient createNewIngredient(String ingredientText) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingredientText);
        newIngredient.setLanguage("es");
        return ingredientRepository.save(newIngredient);
    }

    @Override
    public List<Recipe> loadRecipeBase() {
        List<String> urls = new ArrayList<>();
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource("recipes_es.txt").toURI());
             urls = Files.readAllLines(path);
             return createRecipeListFromUrl(urls);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
