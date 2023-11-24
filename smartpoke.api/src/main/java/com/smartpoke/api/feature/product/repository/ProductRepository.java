package com.smartpoke.api.feature.product.repository;

import com.smartpoke.api.feature.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
