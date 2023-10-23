package com.smartpoke.api.repository.products;

import com.smartpoke.api.model.products.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
