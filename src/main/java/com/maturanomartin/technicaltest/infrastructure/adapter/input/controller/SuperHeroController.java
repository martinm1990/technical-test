package com.maturanomartin.technicaltest.infrastructure.adapter.input.controller;

import com.maturanomartin.technicaltest.application.ports.input.superhero.GetAllSuperHeroUseCase;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.dto.SuperHeroResponseDto;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.mapper.SuperHeroRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/super-heroes")
@RequiredArgsConstructor
public class SuperHeroController {

    private final SuperHeroRestMapper superHeroRestMapper;
    private final GetAllSuperHeroUseCase getAllSuperHeroUseCase;

    @GetMapping()
    @Operation(summary = "Get all superheroes", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found Superheros")})
    public ResponseEntity<List<SuperHeroResponseDto>> getAllSuperHeroHandler(@RequestParam(required = false) String name) {
        var superHeroes = Objects.isNull(name)
                ? getAllSuperHeroUseCase.getAll()
                : getAllSuperHeroUseCase.getAllByName(name);
        return new ResponseEntity<>(superHeroRestMapper.toResponseListDto(superHeroes), HttpStatus.OK);
    }

}
