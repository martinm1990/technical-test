package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.input.superhero.GetSuperHeroByIdUseCase;
import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.exception.SuperHeroNotFound;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSuperHeroByIdUseCaseImpl implements GetSuperHeroByIdUseCase {

    private final SuperHeroOutputPort superHeroOutputPort;

    @Override
    public SuperHero apply(Long id) {
        return superHeroOutputPort
                .getSuperHeroById(id)
                .orElseThrow(() ->
                        new SuperHeroNotFound("Product not found with id " + id));
    }
}
