package com.smartpoke.api.feature.recipe.repository;

import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
import com.smartpoke.api.feature.recipe.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findByDifficultyEnum(DifficultyEnum difficult, Pageable pageable);
    @Query("SELECT r FROM Recipe r WHERE (:name IS NULL OR r.name LIKE %:name%)")
    Page<Recipe> findByNameContaining(String name, Pageable pageable);
}
