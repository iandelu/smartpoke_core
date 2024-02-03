package com.smartpoke.api.feature.planner.repository;

import com.smartpoke.api.feature.planner.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepository extends JpaRepository<Menu, Long> {
}
