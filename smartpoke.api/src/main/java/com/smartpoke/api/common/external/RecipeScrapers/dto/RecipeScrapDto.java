package com.smartpoke.api.common.external.RecipeScrapers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.smartpoke.api.common.utils.NumberExtractor;
import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.recipe.model.*;
import lombok.Data;

import java.util.ArrayList;
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
    private String dinners;
    private String description;
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
    private List<String> categories = new ArrayList<>();

    @JsonSetter("cuisine")
    public void setCuisine(String cuisine) {
        if (cuisine != null && !cuisine.isEmpty()) {
            categories.add(cuisine);
        }
    }

    @JsonSetter("category")
    public void setCategory(String category) {
        if (category != null && !category.isEmpty()) {
            categories.add(category);
        }
    }

    public Recipe toEntity(){
        Recipe recipe = new Recipe();
        recipe.setName(this.title);
        recipe.setDescription(this.description);
        recipe.setPicture(this.image);
        recipe.setPrepTime(this.totalTime);
        recipe.setDiners(NumberExtractor.getIntPosition(this.dinners, 0));
        recipe.setSource(this.url);
        recipe.setLan(this.lan);
        recipe.setDifficultyEnum(DifficultyEnum.calculateDifficult(this.totalTime));
        recipe.setRating(this.ratings);
        NutrientsRecipe nutrients = this.nutrients != null ? this.nutrients.toEntity() : new NutrientsRecipe();
        recipe.setNutrientsRecipe(nutrients);


        List<RecipeStep> recipeSteps = new ArrayList<>();
        if(this.steps != null && !this.steps.isEmpty()){
            for (int i = 0; i < this.steps.size(); i++) {
                recipeSteps.add(textToStep(this.steps.get(i), i+1));
            }
        }
        recipe.setRecipeSteps(recipeSteps);


        return recipe;
    }

    private RecipeStep textToStep(String text, Integer position) {
        RecipeStep step = new RecipeStep();
        step.setDescription(text);
        step.setPosition(position);
        step.setTime(NumberExtractor.sumTimes(text));
        step.setPicture(null);
        return step;
    }


}
