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
    @Query(value = "SELECT * FROM get_recipes_by_criteria(:searchTerm)", nativeQuery = true)
    Page<Recipe> findByNameContaining(String searchTerm, Pageable pageable);



}
