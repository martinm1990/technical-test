package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GetAllSuperHeroUseCaseImplTest {

    private SuperHeroOutputPort superHeroOutputPort;

    @BeforeEach
    void setUp(){
        this.superHeroOutputPort = Mockito.mock(SuperHeroOutputPort.class);
    }

    @Test
    void whenGetAll_thenReturnAllSuperHeroes() {

        //Given
        Mockito.when(superHeroOutputPort.getAllSuperHero()).thenReturn(getAllSuperHero(null));

        //When
        List<SuperHero> superHeroes = this.superHeroOutputPort.getAllSuperHero();

        //Then
        assertNotNull(superHeroes);
        assertEquals(superHeroes.size(), 2);
        Mockito.verify(superHeroOutputPort, Mockito.times(1)).getAllSuperHero();

    }

    @Test
    void WhenGetAllByName_thenGetAllSuperhero() {
        //Given
        String name = "pider";
        Mockito.when(superHeroOutputPort.getAllSuperHeroByName(name)).thenReturn(getAllSuperHero(name));

        //When
        var superHeroes = this.superHeroOutputPort.getAllSuperHeroByName(name);

        //Then
        assertNotNull(superHeroes);
        assertEquals(superHeroes.size(), 1);
        Mockito.verify(superHeroOutputPort, Mockito.times(1)).getAllSuperHeroByName(name);
    }

    private List<SuperHero> getAllSuperHero(String name) {

        List<SuperHero> superHeroes = List.of(
                SuperHero.builder().id(1L).name("Superman").age(100).skill("Super Strength").build(),
                SuperHero.builder().id(2L).name("Spiderman").age(17).skill("Super Strength").build()
        );

        if (Objects.nonNull(name)){
            superHeroes = superHeroes
                    .stream()
                    .filter(superHero -> superHero.getName().contains(name))
                    .collect(Collectors.toList());
        }

        return superHeroes;
    }

}