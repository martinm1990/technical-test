package com.maturanomartin.technicaltest.application.ports.input.superhero;

@FunctionalInterface
public interface DeleteSuperHeroUseCase {

    void apply(Long id);

}
