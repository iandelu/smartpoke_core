package com.smartpoke.api.feature.recipe.repository;

import com.smartpoke.api.feature.recipe.model.RecipeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientsRepository extends JpaRepository<RecipeProduct, Long> {
}
