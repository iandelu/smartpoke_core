package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.external.RecipeScrapers.RecipeScraperClient;
import com.smartpoke.api.common.external.RecipeScrapers.dto.RecipeScrapDto;
import com.smartpoke.api.common.utils.NumberExtractor;
import com.smartpoke.api.feature.product.model.Ingredient;
import com.smartpoke.api.feature.product.repository.IngredientRepository;
import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.recipe.model.RecipeIngredient;
import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;
import com.smartpoke.api.feature.recipe.repository.RecipeIngredientsRepository;
import com.smartpoke.api.feature.recipe.repository.RecipeRepository;
import com.smartpoke.api.feature.recipe.repository.RecipeStepRepository;
import com.smartpoke.api.feature.recipe.repository.UnitOfMesureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    private UnitOfMesureRepository unitOfMesureRepository;


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
            if (recipeScrapDto!=null){
                Recipe recipe = recipeScrapDto.toEntity();
                recipe.setRecipeIngredients(convertIngredients(recipeScrapDto.getIngredients()));

                return recipeRepository.save(recipe);
            }
            throw new ResourceNotFoundException("Not possible to find this recipe, try later");
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
        String unit;
        String ingredient = "";

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientText(text);

        Pattern numberPattern = Pattern.compile("(\\d+\\.?\\d*)");
        Matcher numberMatcher = numberPattern.matcher(text);

        double amount = 0;
        if (numberMatcher.find()) {
            amount = Double.parseDouble(numberMatcher.group(1));
        }

        String[] parts = text.split("\\d+\\.?\\d*");
        String restOfText = parts.length > 1 ? parts[1].trim() : parts[0].trim();



        int firstSpaceIndex = restOfText.indexOf(' ');
        if (firstSpaceIndex != -1) {
            unit = restOfText.substring(0, firstSpaceIndex);
            ingredient = restOfText.substring(firstSpaceIndex).trim();
        } else {
            unit = "";
            ingredient = restOfText;
        }

        recipeIngredient.setAmount(amount);
        UnitOfMeasure unitOfMeasure = unitOfMesureRepository.findByName(unit)
                .orElseGet(() -> createNewUnit(unit));
        recipeIngredient.setUnitOfMeasure(unitOfMeasure);

        String finalIngredient = ingredient;
        Ingredient ingredientEntity = ingredientRepository.findByName(ingredient)
                .orElseGet(() -> createNewIngredient(finalIngredient));
        recipeIngredient.setIngredient(ingredientEntity);

        return recipeIngredient;
    }

    private UnitOfMeasure createNewUnit(String unit) {
        UnitOfMeasure unitOfMeasure= new UnitOfMeasure();
        unitOfMeasure.setName(unit);

        return unitOfMesureRepository.save(unitOfMeasure);
    }

    private Ingredient createNewIngredient(String ingredientText) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingredientText);
        newIngredient.setLanguage("es");
        return ingredientRepository.save(newIngredient);
    }

    @Override
    public List<Recipe> loadRecipeBase() {
        List<String> urls = RecipeScraperClient.loadUrls();
        return createRecipeListFromUrl(urls);
    }
}
