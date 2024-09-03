package com.smartpoke.api.feature.product.repository;

import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findByName(String name);
    Optional<Product> findFirstByEan(String ean);
    Optional<Product> findFirstByEanOrName(@Param("ean")String ean, @Param("name") String name);

    Optional<Product> findMostSimilarByName(String name);
    @Query(value = "SELECT p.* " +
            "FROM product p, " +
            "     unnest(string_to_array(:tokens, ' ')) as token " +
            "GROUP BY p.id " +
            "ORDER BY avg(similarity(token, p.description)) DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<Product> findMostSimilarProduct(@Param("tokens") String tokens);
    boolean existsByEan(String ean);
}
