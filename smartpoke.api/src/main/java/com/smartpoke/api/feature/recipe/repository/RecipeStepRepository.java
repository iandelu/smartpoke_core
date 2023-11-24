package com.smartpoke.api.feature.recipe.repository;

import com.smartpoke.api.feature.recipe.model.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {
}
