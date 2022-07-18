package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GetSuperHeroByIdUseCaseImplTest {

    private SuperHeroOutputPort superHeroOutputPort;

    @BeforeEach
    void setUp() {
        this.superHeroOutputPort = Mockito.mock(SuperHeroOutputPort.class);
    }

    @Test
    void whenGetSuperHeroById_thenReturnSuperHero() {
        //Given
        var id = 5L;
        Mockito.when(superHeroOutputPort.getSuperHeroById(id)).thenReturn(getSuperHeroById(id));

        //When
        var superHero = this.superHeroOutputPort.getSuperHeroById(id);

        //Then
        assertNotNull(superHero.get());
    }

    private Optional<SuperHero> getSuperHeroById(Long id) {

        List<SuperHero> superHeroes = List.of(
                SuperHero.builder().id(5L).name("Superman").age(100).skill("Super Strength").build(),
                SuperHero.builder().id(2L).name("Spiderman").age(17).skill("Super Strength").build()
        );


        return superHeroes.stream().filter(superHero -> superHero.getId().equals(id)).findFirst();

    }
}