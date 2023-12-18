package com.smartpoke.api.common.external.RecipeScrapers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartpoke.api.common.utils.NumberExtractor;
import com.smartpoke.api.feature.recipe.model.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RecipeScrapDto {
    @JsonProperty("author")
    private String author;
    @JsonProperty("canonical_url")
    private String url;
    @JsonProperty("title")
    private String title;
    @JsonProperty("total_time")
    private Integer totalTime;
    @JsonProperty("yields")
    private String servings;
    private String description;
    private String cuisine;
    private String category;
    private Double ratings;
    @JsonProperty("image")
    private String image;
    @JsonProperty("ingredients")
    private List<String> ingredients;
    @JsonProperty("instructions_list")
    private List<String> steps;
    @JsonProperty("language")
    private String lan;
    @JsonProperty("recipeResponse")
    private NutrientsRecipeDto nutrients;

    public Recipe toEntity(){
        Recipe recipe = new Recipe();
        recipe.setName(this.title);
        recipe.setDescription(this.description);
        recipe.setPicture(this.image);
        recipe.setPrepTime(this.totalTime);
        recipe.setDiners(NumberExtractor.getIntPosition(this.servings, 0));
        recipe.setSource(this.url);
        recipe.setLan(this.lan);
        recipe.setDifficultyEnum(DifficultyEnum.calculateDifficult(this.totalTime));
        recipe.setRating(this.ratings);
        NutrientsRecipe nutrients = this.nutrients != null ? this.nutrients.toEntity() : new NutrientsRecipe();
        recipe.setNutrientsRecipe(nutrients);


        Set<RecipeStep> recipeSteps = new HashSet<>();
        if(this.steps != null && !this.steps.isEmpty()){
            for (int i = 0; i < this.steps.size(); i++) {
                recipeSteps.add(textToIngredient(this.steps.get(i), i+1));
            }
        }
        recipe.setRecipeSteps(recipeSteps);


        return recipe;
    }

    private RecipeStep textToIngredient(String text, Integer position) {
        RecipeStep step = new RecipeStep();
        step.setDescription(text);
        step.setPosition(position);
        step.setTime(NumberExtractor.getIntPosition(text, 0));
        step.setPicture(null);
        return step;
    }


}
