package com.maturanomartin.technicaltest.infrastructure.adapter.input.controller;

import com.maturanomartin.technicaltest.application.ports.input.superhero.GetAllSuperHeroUseCase;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.mapper.SuperHeroRestMapper;
import com.maturanomartin.technicaltest.shared.security.config.JwtRequestFilter;
import com.maturanomartin.technicaltest.shared.security.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = TestSecurityConfig.class)
class SuperHeroControllerTest {

    private static final String GET_ALL_SUPERHEROES_URL = "/v1/super-heroes";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllSuperHeroUseCase getAllSuperHeroUseCase;

    @MockBean
    private SuperHeroRestMapper superHeroRestMapper;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @Test
    void whenGetAllSuperHero_thenCallGetAllSuperUseCase() throws Exception {
        //Given
        var request = MockMvcRequestBuilders.get(GET_ALL_SUPERHEROES_URL)
                .header("Authorization: ", "123456");
        //When
        mockMvc.perform(request);
        //then
        Mockito.verify(getAllSuperHeroUseCase, Mockito.times(1)).getAll();
    }

    @Test
    void whenGetAllSuperHeroByName_thenCallGetAllSuperUseCaseWithName() throws Exception {
        //Given
        var request = MockMvcRequestBuilders.get(GET_ALL_SUPERHEROES_URL)
                .header("Authorization: ", "123456")
                .param("name", "super");
        //When
        mockMvc.perform(request);
        //then
        Mockito.verify(getAllSuperHeroUseCase, Mockito.times(1)).getAllByName("super");
    }
}