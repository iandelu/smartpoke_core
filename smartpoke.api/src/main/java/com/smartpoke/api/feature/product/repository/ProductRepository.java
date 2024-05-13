package com.smartpoke.api.feature.product.repository;

import com.smartpoke.api.feature.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findByEan(String ean);
    Optional<Product> findByEanOrName(@Param("ean")String ean, @Param("name") String name);

    Optional<Product> findMostSimilarByName(String name);
    @Query(value = "SELECT * FROM search_product_by_description(:searchQuery)", nativeQuery = true)
    Optional<Product> findMostSimilarByName(@Param("searchQuery") String[] searchQuery);
    boolean existsByEan(String ean);
}
