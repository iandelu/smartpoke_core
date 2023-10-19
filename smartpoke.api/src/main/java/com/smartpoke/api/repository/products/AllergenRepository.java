package com.smartpoke.api.repository.products;

import com.smartpoke.api.model.products.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<Allergen, Long> {
}
