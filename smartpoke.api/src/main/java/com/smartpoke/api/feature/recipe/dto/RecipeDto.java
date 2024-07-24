package com.smartpoke.api.feature.recipe.dto;

import com.smartpoke.api.common.model.Nutrients;
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
    private Integer totalTime;
    private Double price;
    private Integer yields;
    private String pictureUrl;

    private String source;
    private String lan;
    private Double ratings;
    private Integer views;
    private String videoUrl;
    private Date lastUpdateDate;
    private String image;

    private DifficultyEnum difficulty;
    private Nutrients nutrients;
    private List<RecipeStep> recipeSteps = new ArrayList<>();
    private List<RecipeProduct> recipeProducts = new ArrayList<>();
    private Set<Category> categories = new HashSet<>();
    private User user;
    private byte[] imageFile;
}
