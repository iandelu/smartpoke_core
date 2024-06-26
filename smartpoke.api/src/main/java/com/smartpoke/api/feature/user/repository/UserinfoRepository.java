package com.smartpoke.api.feature.user.repository;


import com.smartpoke.api.feature.user.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo, Long> {
}
