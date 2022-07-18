package com.maturanomartin.technicaltest.application.ports.output;

import com.maturanomartin.technicaltest.domain.event.SuperHeroCreatedEvent;
import com.maturanomartin.technicaltest.domain.event.SuperHeroDeletedEvent;
import com.maturanomartin.technicaltest.domain.event.SuperHeroUpdatedEvent;

public interface SuperHeroEventPublisher {

    void publishSuperHeroCreatedEvent(SuperHeroCreatedEvent superHeroCreatedEvent);

    void publishSuperHeroUpdatedEvent(SuperHeroUpdatedEvent superHeroUpdatedEvent);

    void publishSuperHeroDeletedEvent(SuperHeroDeletedEvent superHeroDeletedEvent);

}
