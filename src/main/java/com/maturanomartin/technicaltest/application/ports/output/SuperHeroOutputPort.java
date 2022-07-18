package com.maturanomartin.technicaltest.application.ports.output;

import com.maturanomartin.technicaltest.domain.model.SuperHero;

import java.util.List;

public interface SuperHeroOutputPort {

    List<SuperHero> getAllSuperHero();
    List<SuperHero> getAllSuperHeroByName(String name);
}
