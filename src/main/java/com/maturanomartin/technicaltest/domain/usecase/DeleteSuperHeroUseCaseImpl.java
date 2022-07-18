package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.input.superhero.DeleteSuperHeroUseCase;
import com.maturanomartin.technicaltest.application.ports.output.SuperHeroEventPublisher;
import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.event.SuperHeroDeletedEvent;
import com.maturanomartin.technicaltest.domain.exception.SuperHeroNotFound;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSuperHeroUseCaseImpl implements DeleteSuperHeroUseCase {

    private final SuperHeroOutputPort superHeroOutputPort;
    private final SuperHeroEventPublisher superHeroEventPublisher;

    @Override
    public void apply(Long id) {

        SuperHero superHero = superHeroOutputPort.getSuperHeroById(id)
                .orElseThrow(() ->
                new SuperHeroNotFound("Product not found with id " + id));

       superHeroOutputPort.delete(superHero);

       superHeroEventPublisher.publishSuperHeroDeletedEvent(new SuperHeroDeletedEvent(id));
    }
}
