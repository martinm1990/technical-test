package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateSuperHeroUseCaseImplTest {
    private SuperHeroOutputPort superHeroOutputPort;

    @BeforeEach
    void setUp(){
        this.superHeroOutputPort = Mockito.mock(SuperHeroOutputPort.class);
    }

    @Test
    void ifUpdateSuperHeroIsOk_thenReturnSuperHero(){
        //Given

        var superHero = SuperHero.builder().id(4L).name("Flassssh").age(27).skill("move much faster").build();
        var savedSuperHero = SuperHero.builder().id(4L).name("Flash").age(27).skill("move much faster").build();

        Mockito.when(superHeroOutputPort.save(superHero)).thenReturn(savedSuperHero);

        //When
        var resultSavedSuperHero = this.superHeroOutputPort.save(superHero);

        //Then
        assertNotNull(resultSavedSuperHero);
        assertNotNull(resultSavedSuperHero.getId());
        Mockito.verify(superHeroOutputPort, Mockito.times(1)).save(superHero);
    }
}