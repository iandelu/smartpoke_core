package com.smartpoke.api.repository.recipes;

import com.smartpoke.api.model.recipes.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {
}
