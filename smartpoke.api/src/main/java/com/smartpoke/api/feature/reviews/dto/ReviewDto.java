package com.smartpoke.api.feature.reviews.dto;

import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.user.model.User;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewDto {
    Long id;
    Double rating;
    String comment;
    String picture;
    Long recipeId;
    Long userId;
}
