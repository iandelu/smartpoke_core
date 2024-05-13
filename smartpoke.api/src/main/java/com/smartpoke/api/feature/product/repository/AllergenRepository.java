package com.smartpoke.api.feature.product.repository;

import com.smartpoke.api.feature.product.model.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergenRepository extends JpaRepository<Allergen, Long> {
    @Query("SELECT a FROM Allergen a WHERE a.name = :name and a.lan = :lan")
    Optional<Allergen> findByName(@Param("name") String name, @Param("lan") String lan);

    void deleteByName(String name);
}
