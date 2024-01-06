package com.smartpoke.api.feature.category.repository;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

}
