package com.smartpoke.api.feature.recipe.dto;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
import com.smartpoke.api.feature.recipe.model.NutrientsRecipe;
import com.smartpoke.api.feature.recipe.model.RecipeProduct;
import com.smartpoke.api.feature.recipe.model.RecipeStep;
import com.smartpoke.api.feature.user.model.User;
import lombok.Data;

import java.util.*;

@Data
public class RecipeDto {

    private Long id;
    private String name;
    private String description;
    private Integer prepTime;
    private Double price;
    private Integer diners;
    private String pictureUrl;
    private byte[] pictureFile;
    private String source;
    private String lan;
    private Double rating;
    private Integer views;
    private String videoUrl;
    private Date lastUpdateDate;


    private DifficultyEnum difficultyEnum;
    private NutrientsRecipe nutrientsRecipe;
    private List<RecipeStep> recipeSteps = new ArrayList<>();
    private List<RecipeProduct> recipeProducts = new ArrayList<>();
    private Set<Category> categories = new HashSet<>();
    private User user;
}
