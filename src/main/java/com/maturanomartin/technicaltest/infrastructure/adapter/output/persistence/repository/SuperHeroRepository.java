package com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.repository;

import com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.entity.SuperHeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHeroEntity,Long> {
    List<SuperHeroEntity> findByNameContainsIgnoreCase(String name);
}
