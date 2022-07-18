package com.maturanomartin.technicaltest.application.ports.input.superhero;

import com.maturanomartin.technicaltest.domain.model.SuperHero;

import java.util.List;

public interface GetAllSuperHeroUseCase {

    List<SuperHero> getAll();
    List<SuperHero> getAllByName(String name);
}
