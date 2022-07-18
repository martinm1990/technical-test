package com.maturanomartin.technicaltest.infrastructure.adapter.output.eventpublisher;

import com.maturanomartin.technicaltest.application.ports.output.SuperHeroEventPublisher;
import com.maturanomartin.technicaltest.domain.event.SuperHeroCreatedEvent;
import com.maturanomartin.technicaltest.domain.event.SuperHeroDeletedEvent;
import com.maturanomartin.technicaltest.domain.event.SuperHeroUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class SuperHeroEventPublisherImpl implements SuperHeroEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public SuperHeroEventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishSuperHeroCreatedEvent(SuperHeroCreatedEvent superHeroCreatedEvent) {
        applicationEventPublisher.publishEvent(superHeroCreatedEvent);
    }

    @Override
    public void publishSuperHeroUpdatedEvent(SuperHeroUpdatedEvent superHeroUpdatedEvent) {
        applicationEventPublisher.publishEvent(superHeroUpdatedEvent);
    }

    @Override
    public void publishSuperHeroDeletedEvent(SuperHeroDeletedEvent superHeroDeletedEvent) {
        applicationEventPublisher.publishEvent(superHeroDeletedEvent);
    }
}
