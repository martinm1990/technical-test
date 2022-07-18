package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.input.superhero.UpdateSuperHeroUseCase;
import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSuperHeroUseCaseImpl implements UpdateSuperHeroUseCase {

    private final SuperHeroOutputPort superHeroOutputPort;

    @Override
    @CachePut(cacheNames = "superheroes", key = "#superHero.id")
    public SuperHero apply(SuperHero superHero) {
        superHero = superHeroOutputPort.save(superHero);
        return superHero;
    }
}
