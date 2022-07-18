package com.maturanomartin.technicaltest.infrastructure.adapter.input.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.dto.SuperHeroRequestDto;
import com.maturanomartin.technicaltest.shared.security.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestSecurityConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SuperHeroControllerTestIntegration {

    private static final String GET_ALL_SUPERHEROES_URL = "/v1/super-heroes";

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = "admin")
    @Test
    void testGetAllSuperHeroHandler() throws Exception {
        var request = MockMvcRequestBuilders.get(GET_ALL_SUPERHEROES_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization: ", "123456")
                .param("name", "super");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].age").isNumber())
                .andExpect(jsonPath("$[0].skill").isNotEmpty())
                .andReturn();
    }

    @WithMockUser(value = "admin")
    @Test
    void testGetSuperHeroByIdHandler() throws Exception {
        var request = MockMvcRequestBuilders.get(GET_ALL_SUPERHEROES_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization: ", "123456")
                .param("name", "super");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.skill").isNotEmpty())
                .andReturn();
    }

    @WithMockUser(value = "admin")
    @Test
    void testCreateSuperHeroHandler() throws Exception {

        var superRequestDto = new SuperHeroRequestDto("flash",25,"run very fast");
        String dummyRequestBodyJson = new ObjectMapper().writeValueAsString(superRequestDto);

        var request = MockMvcRequestBuilders.post(GET_ALL_SUPERHEROES_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization: ", "123456")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content(dummyRequestBodyJson);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.skill").isNotEmpty())
                .andReturn();
    }

    @WithMockUser(value = "admin")
    @Test
    void testUpdateSuperHeroHandler() throws Exception {

        var superRequestDto = new SuperHeroRequestDto("flash",25,"run very fast");
        String dummyRequestBodyJson = new ObjectMapper().writeValueAsString(superRequestDto);

        var request = MockMvcRequestBuilders.put(GET_ALL_SUPERHEROES_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization: ", "123456")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content(dummyRequestBodyJson);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.skill").isNotEmpty())
                .andReturn();
    }

    @Test
    @WithMockUser(value = "admin")
    void testDeleteSuperHeroHandler() throws Exception {

        var request = MockMvcRequestBuilders.delete(GET_ALL_SUPERHEROES_URL+"/2")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization: ", "123456");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

}
