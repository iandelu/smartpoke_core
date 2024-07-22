package com.smartpoke.api.feature.recipe.specification;

import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
import com.smartpoke.api.feature.recipe.model.Recipe;
import org.springframework.data.jpa.domain.Specification;
import java.util.Set;

public class RecipeSpecification {
    public static Specification<Recipe> nameLike(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isEmpty()) {
                return cb.conjunction(); // No filter for name
            } else {
                return cb.like(root.get("name"), "%" + name + "%");
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
        return categories == null || categories.isEmpty() ? null : (root, query, cb) -> root.join("categories").get("name").in(categories);
    }
}
