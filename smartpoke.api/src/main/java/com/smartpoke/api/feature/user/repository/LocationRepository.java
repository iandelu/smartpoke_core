package com.smartpoke.api.feature.user.repository;

import com.smartpoke.api.feature.user.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
