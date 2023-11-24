package com.smartpoke.api.feature.product.repository;

import com.smartpoke.api.feature.product.model.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<Allergen, Long> {
}
