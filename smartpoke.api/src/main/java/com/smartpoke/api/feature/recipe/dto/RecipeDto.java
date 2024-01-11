package com.smartpoke.api.feature.recipe.dto;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
import com.smartpoke.api.feature.recipe.model.NutrientsRecipe;
import com.smartpoke.api.feature.recipe.model.RecipeIngredient;
import com.smartpoke.api.feature.recipe.model.RecipeStep;
import com.smartpoke.api.feature.user.model.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private DifficultyEnum difficultyEnum;
    private NutrientsRecipe nutrientsRecipe;
    private List<RecipeStep> recipeSteps = new ArrayList<>();
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    private Set<Category> categories = new HashSet<>();
    private User user;
}
