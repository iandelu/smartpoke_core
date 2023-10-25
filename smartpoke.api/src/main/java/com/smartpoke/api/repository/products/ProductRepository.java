package com.smartpoke.api.repository.products;

import com.smartpoke.api.model.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
