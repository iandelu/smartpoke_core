package com.smartpoke.api.feature.recipe.repository;

import com.smartpoke.api.feature.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
