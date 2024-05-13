package com.smartpoke.api.feature.recipe.dto;

import com.smartpoke.api.common.ImageStorage.ImageStorageService;
import com.smartpoke.api.common.utils.RecipeIngredientComparator;
import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.recipe.model.RecipeProduct;
import com.smartpoke.api.feature.recipe.model.RecipeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RecipeMapper {

    @Autowired
    static ImageStorageService imageStorageService;

    public static Recipe toEntity(RecipeDto dto) {
        Recipe recipe = new Recipe();
        recipe.setId(dto.getId());
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setPrice(dto.getPrice());
        recipe.setDiners(dto.getDiners());
        recipe.setPicture(dto.getPictureUrl());
        recipe.setSource(dto.getSource());
        recipe.setLan(dto.getLan());
        recipe.setRating(dto.getRating());
        recipe.setDifficultyEnum(dto.getDifficultyEnum());
        recipe.setNutrientsRecipe(dto.getNutrientsRecipe());
        recipe.setRecipeSteps(dto.getRecipeSteps());
        recipe.setVideoUrl(dto.getVideoUrl());
        recipe.setViews(dto.getViews());
        recipe.setLastUpdateDate(dto.getLastUpdateDate());


        dto.getRecipeProducts().sort(new RecipeIngredientComparator());
        recipe.setRecipeProducts(new HashSet<>(dto.getRecipeProducts()));

        recipe.setCategories(dto.getCategories());
        recipe.setUser(dto.getUser());

        if (dto.getPictureFile() != null && dto.getPictureFile().length > 0) {
            String destination = ImageStorageService.storeImage(dto.getPictureFile());
            recipe.setPicture(destination);
        }

        return recipe;
    }

    public static RecipeDto toDto(Recipe entity) {
        RecipeDto dto = new RecipeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrepTime(entity.getPrepTime());
        dto.setPrice(entity.getPrice());
        dto.setDiners(entity.getDiners());
        dto.setPictureUrl(entity.getPicture());
        dto.setSource(entity.getSource());
        dto.setLan(entity.getLan());
        dto.setRating(entity.getRating());
        dto.setDifficultyEnum(entity.getDifficultyEnum());
        dto.setNutrientsRecipe(entity.getNutrientsRecipe());
        dto.setViews(entity.getViews());
        dto.setLastUpdateDate(entity.getLastUpdateDate());
        dto.setVideoUrl(entity.getVideoUrl());

        entity.getRecipeSteps().sort(Comparator.comparing(RecipeStep::getPosition));
        dto.setRecipeSteps(entity.getRecipeSteps());

        List<RecipeProduct> sortedIngredients = new ArrayList<>(entity.getRecipeProducts());
        sortedIngredients.sort(new RecipeIngredientComparator());
        dto.setRecipeProducts(sortedIngredients);
        dto.setCategories(entity.getCategories());
        dto.setUser(entity.getUser());

        byte[] pictureBytes = ImageStorageService.dowloadImage(entity.getPicture());
        dto.setPictureFile(pictureBytes);

        return dto;
    }
}
