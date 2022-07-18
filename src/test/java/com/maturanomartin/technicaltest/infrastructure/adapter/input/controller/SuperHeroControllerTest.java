package com.maturanomartin.technicaltest.infrastructure.adapter.input.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maturanomartin.technicaltest.application.ports.input.superhero.CreateSuperHeroUseCase;
import com.maturanomartin.technicaltest.application.ports.input.superhero.GetAllSuperHeroUseCase;
import com.maturanomartin.technicaltest.application.ports.input.superhero.GetSuperHeroByIdUseCase;
import com.maturanomartin.technicaltest.application.ports.input.superhero.UpdateSuperHeroUseCase;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.dto.SuperHeroRequestDto;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.mapper.SuperHeroRestMapper;
import com.maturanomartin.technicaltest.shared.security.config.JwtRequestFilter;
import com.maturanomartin.technicaltest.shared.security.config.TestSecurityConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = TestSecurityConfig.class)
class SuperHeroControllerTest {

    private static final String SUPERHEROES_URL = "/v1/super-heroes";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllSuperHeroUseCase getAllSuperHeroUseCase;

    @MockBean
    private GetSuperHeroByIdUseCase getSuperHeroByIdUseCase;

    @MockBean
    private CreateSuperHeroUseCase createSuperHeroUseCase;

    @MockBean
    private UpdateSuperHeroUseCase updateSuperHeroUseCase;

    @MockBean
    private SuperHeroRestMapper superHeroRestMapper;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @Test
    void whenGetAllSuperHero_thenCallGetAllSuperUseCase() throws Exception {
        //Given
        var request = MockMvcRequestBuilders.get(SUPERHEROES_URL)
                .header("Authorization: ", "123456");
        //When
        mockMvc.perform(request);
        //then
        Mockito.verify(getAllSuperHeroUseCase, Mockito.times(1)).getAll();
    }

    @Test
    void whenGetAllSuperHeroByName_thenCallGetAllSuperUseCaseWithName() throws Exception {
        //Given
        var request = MockMvcRequestBuilders.get(SUPERHEROES_URL)
                .header("Authorization: ", "123456")
                .param("name", "super");
        //When
        mockMvc.perform(request);
        //then
        Mockito.verify(getAllSuperHeroUseCase, Mockito.times(1)).getAllByName("super");
    }

    @Test
    void whenGetSuperHeroById_thenCallGetSuperHeroByIdUseCase() throws Exception {
        //Given
        var request = MockMvcRequestBuilders.get(SUPERHEROES_URL + "/1")
                .header("Authorization: ", "123456");
        //When
        mockMvc.perform(request);
        //then
        Mockito.verify(getSuperHeroByIdUseCase, Mockito.times(1)).apply(1L);
    }

    @Test
    void whenCreateSuperHeroWhitMissingProperties_thenThrownBadRequest() throws Exception {
        //Given
        var superRequestDto = new SuperHeroRequestDto();
        String dummyRequestBodyJson = new ObjectMapper().writeValueAsString(superRequestDto);
        var superHero = new SuperHero();

        var request = MockMvcRequestBuilders.post(SUPERHEROES_URL)
                .header("Authorization: ", "123456")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dummyRequestBodyJson);
        //When
        ResultActions resultActions = mockMvc.perform(request);

        //then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void whenCreateSuperHero_thenCallCreateSuperHeroUseCase() throws Exception {
        //Given
        var superRequestDto = new SuperHeroRequestDto("Superman",100,"x ray vision");
        String dummyRequestBodyJson = new ObjectMapper().writeValueAsString(superRequestDto);
        var superHero = superHeroRestMapper.toModel(superRequestDto);

        var request = MockMvcRequestBuilders.post(SUPERHEROES_URL)
                .header("Authorization: ", "123456")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dummyRequestBodyJson);
        //When
        ResultActions resultActions = mockMvc.perform(request);
        //then
        Mockito.verify(createSuperHeroUseCase, Mockito.times(1)).apply(superHero);
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @Disabled
    void whenUpdateSuperHero_thenCallUpdateSuperHeroUseCase() throws Exception {
        //Given
        var superRequestDto = new SuperHeroRequestDto("Superman",100,"x-ray vision");
        String dummyRequestBodyJson = new ObjectMapper().writeValueAsString(superRequestDto);
        var superHero = SuperHero.builder().id(1L).name("Superman").age(100).skill("x-ray vision").build();

        var request = MockMvcRequestBuilders.put(SUPERHEROES_URL+"/1")
                .header("Authorization: ", "123456")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content(dummyRequestBodyJson);
        //When
        ResultActions resultActions = mockMvc.perform(request);

        //then
        Mockito.verify(updateSuperHeroUseCase, Mockito.times(1)).apply(superHero);
        resultActions.andExpect(status().isOk());
    }
}