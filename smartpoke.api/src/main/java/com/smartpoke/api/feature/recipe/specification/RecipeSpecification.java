package com.smartpoke.api.feature.recipe.specification;

import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
import com.smartpoke.api.feature.recipe.model.Recipe;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import java.util.Set;

public class RecipeSpecification {

    public static Specification<Recipe> nameOrDescriptionLike(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isEmpty()) {
                return cb.conjunction(); // No filter
            } else {
                String likePattern = "%" + value.toUpperCase() + "%";
                return cb.or(
                        cb.like(cb.upper(root.get("name")), likePattern),
                        cb.like(cb.upper(root.get("description")), likePattern)
                );
            }
        };
    }

    public static Specification<Recipe> ratingGreaterThanOrEqualTo(Integer rating) {
        return rating == null ? null : (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("rating"), rating);
    }

    public static Specification<Recipe> difficultyEqual(DifficultyEnum difficulty) {
        return difficulty == null ? null : (root, query, cb) -> cb.equal(root.get("difficultyEnum"), difficulty);
    }

    public static Specification<Recipe> timeLessThanOrEqualTo(Integer time) {
        return time == null ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get("prepTime"), time);
    }

    public static Specification<Recipe> categoryIn(Set<String> categories) {
        return (root, query, cb) -> {
            if (categories == null || categories.isEmpty()) {
                return cb.conjunction(); // No filter for categories
            } else {
                return root.join("categories", JoinType.LEFT).get("name").in(categories);
            }
        };
    }

    public static Specification<Recipe> containsIngredient(String ingredient) {
        return (root, query, cb) -> {
            if (ingredient == null || ingredient.isEmpty()) {
                return cb.conjunction(); // No filter for ingredients
            } else {
                return cb.like(cb.upper(root.join("ingredients", JoinType.LEFT).get("name")), "%" + ingredient.toUpperCase() + "%");
            }
        };
    }


}
