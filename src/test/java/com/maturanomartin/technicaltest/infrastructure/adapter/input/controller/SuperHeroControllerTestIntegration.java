package com.maturanomartin.technicaltest.infrastructure.adapter.input.controller;

import com.maturanomartin.technicaltest.shared.security.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TestSecurityConfig.class)
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

}
