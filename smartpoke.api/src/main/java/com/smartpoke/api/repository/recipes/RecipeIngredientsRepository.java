package com.smartpoke.api.repository.recipes;

import com.smartpoke.api.model.recipes.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Long> {
}
