package com.maturanomartin.technicaltest.application.ports.input.superhero;

import com.maturanomartin.technicaltest.domain.model.SuperHero;

@FunctionalInterface
public interface GetSuperHeroByIdUseCase {

    SuperHero apply(Long id);

}
