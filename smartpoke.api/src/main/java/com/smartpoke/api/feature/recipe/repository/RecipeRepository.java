package com.smartpoke.api.feature.recipe.repository;

import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
import com.smartpoke.api.feature.recipe.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {

    Page<Recipe> findByDifficultyEnum(DifficultyEnum difficult, Pageable pageable);
    @Query(value = "SELECT * FROM get_recipes_by_criteria(:searchTerm)", nativeQuery = true)
    Page<Recipe> findByNameContaining(String searchTerm, Pageable pageable);

    @Query("SELECT r FROM Recipe r JOIN r.categories c WHERE c.name = :category ORDER BY r.id")
    Page<Recipe> findByCategoryName(@Param("category") String category, Pageable pageable);

    List<Recipe> findBySource(String url);
}
