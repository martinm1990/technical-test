package com.maturanomartin.technicaltest.application.ports.output;

import com.maturanomartin.technicaltest.domain.model.SuperHero;

import java.util.List;
import java.util.Optional;

public interface SuperHeroOutputPort {

    SuperHero save(SuperHero superHero);

    Optional<SuperHero> getSuperHeroById(Long id);

    List<SuperHero> getAllSuperHero();

    List<SuperHero> getAllSuperHeroByName(String name);

    void delete(SuperHero superHero);
}
