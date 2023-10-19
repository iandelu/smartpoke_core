package com.smartpoke.api.dto;

import lombok.Data;

@Data
public class RecipeDto {

    private Integer idRecipe;
    private String name;
    private String description;
    private Integer time;
    private Integer creatorId;
    private String typePlate;
    private Double price;
    private Double rating;
    private Integer diners;
    private String picture;

    //private List<RecipeIngredients> ingredients;
    //private List<RecipeStep> recipeSteps;
    //private List<AlimentCategory> categories;
}
