package com.smartpoke.api.feature.recipe.repository;

import com.smartpoke.api.feature.product.model.Ingredient;
import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitOfMesureRepository extends JpaRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByName(String name);

}
