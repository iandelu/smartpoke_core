package com.smartpoke.api.feature.category.repository;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query("SELECT DISTINCT c " +
            "FROM Product p " +
            "JOIN p.category c " +
            "ORDER BY c.id")
    List<Category> findCategoriesInUseProduct();

    @Query("SELECT DISTINCT c FROM Recipe p " +
            "JOIN p.categories c " +
            "ORDER BY c.name")
    List<Category> findCategoriesInUseByRecipes();


}
