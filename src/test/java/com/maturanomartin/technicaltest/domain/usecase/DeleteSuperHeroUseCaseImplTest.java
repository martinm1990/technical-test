package com.maturanomartin.technicaltest.domain.usecase;

import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.exception.SuperHeroNotFound;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@Disabled
class DeleteSuperHeroUseCaseImplTest {

    private SuperHeroOutputPort superHeroOutputPort;

    @BeforeEach
    void setUp() {
        this.superHeroOutputPort = Mockito.mock(SuperHeroOutputPort.class);
    }

    @Test
    void whenDeleteAndIdDoesntExist_thenThrowSuperHeroNotFound() {
        //Given
        var id = 5L;
        var superHero = SuperHero.builder().id(1L).name("Flash").age(27).skill("move much faster").build();

        Mockito.when(superHeroOutputPort.getSuperHeroById(id)).thenThrow(new SuperHeroNotFound("Super Hero Not Found"));

        Executable executable = () -> superHeroOutputPort.getSuperHeroById(1L);

        assertThrows(SuperHeroNotFound.class, executable, "When Superhero not found then an exception is thrown");
    }
}