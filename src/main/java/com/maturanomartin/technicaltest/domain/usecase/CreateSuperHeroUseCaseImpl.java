package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.input.superhero.CreateSuperHeroUseCase;
import com.maturanomartin.technicaltest.application.ports.output.SuperHeroEventPublisher;
import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.event.SuperHeroCreatedEvent;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSuperHeroUseCaseImpl implements CreateSuperHeroUseCase {

    private final SuperHeroOutputPort superHeroOutputPort;
    private final SuperHeroEventPublisher superHeroEventPublisher;

    @Override
    public SuperHero apply(SuperHero superHero) {
        superHero = superHeroOutputPort.save(superHero);
        superHeroEventPublisher.publishSuperHeroCreatedEvent(new SuperHeroCreatedEvent(superHero.getId()));
        return superHero;
    }
}
