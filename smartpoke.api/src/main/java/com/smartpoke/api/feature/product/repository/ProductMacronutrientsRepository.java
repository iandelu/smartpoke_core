package com.smartpoke.api.feature.product.repository;

import com.smartpoke.api.feature.product.model.ProductNutrients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMacronutrientsRepository extends JpaRepository<ProductNutrients, Long> {
}
