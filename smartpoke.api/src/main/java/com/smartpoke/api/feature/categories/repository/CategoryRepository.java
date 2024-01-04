package com.smartpoke.api.feature.categories.repository;

import com.smartpoke.api.feature.categories.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
