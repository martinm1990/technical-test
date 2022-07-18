package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.input.superhero.GetAllSuperHeroUseCase;
import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSuperHeroUseCaseImpl implements GetAllSuperHeroUseCase {

    private final SuperHeroOutputPort superHeroOutputPort;

    @Override
    public List<SuperHero> getAll() {
        return superHeroOutputPort.getAllSuperHero();
    }

    @Override
    public List<SuperHero> getAllByName(String name) {
        return superHeroOutputPort.getAllSuperHeroByName(name);
    }
}
