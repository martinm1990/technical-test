package com.maturanomartin.technicaltest.infrastructure.adapter.input.controller;

import com.maturanomartin.technicaltest.application.ports.input.superhero.*;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.dto.SuperHeroRequestDto;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.dto.SuperHeroResponseDto;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.mapper.SuperHeroRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.maturanomartin.technicaltest.infrastructure.util.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/super-heroes")
@RequiredArgsConstructor
public class SuperHeroController {

    private final SuperHeroRestMapper superHeroRestMapper;
    private final GetAllSuperHeroUseCase getAllSuperHeroUseCase;
    private final GetSuperHeroByIdUseCase getSuperHeroByIdUseCase;
    private final CreateSuperHeroUseCase createSuperHeroUseCase;
    private final DeleteSuperHeroUseCase deleteSuperHeroUseCase;
    private final UpdateSuperHeroUseCase updateSuperHeroUseCase;

    @GetMapping()
    @Operation(summary = "Get all superheroes", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found Superheros")})
    @ExecutionTime
    public ResponseEntity<List<SuperHeroResponseDto>> getAllSuperHeroHandler(@RequestParam(required = false) String name) {
        var superHeroes = Objects.isNull(name)
                ? getAllSuperHeroUseCase.getAll()
                : getAllSuperHeroUseCase.getAllByName(name);
        return new ResponseEntity<>(superHeroRestMapper.toResponseListDto(superHeroes), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Superhero By id")
    @ExecutionTime
    public ResponseEntity<SuperHeroResponseDto> getSuperHeroByIdHandler(@PathVariable Long id){
        SuperHero superHero = getSuperHeroByIdUseCase.apply(id);
        return new ResponseEntity<>(superHeroRestMapper.toResponseDto(superHero), HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "Create a Superhero")
    @ExecutionTime
    public ResponseEntity<SuperHeroResponseDto> createSuperHeroHandler(
            @RequestBody @Valid SuperHeroRequestDto superHeroRequestDto){
        SuperHero superHero = superHeroRestMapper.toModel(superHeroRequestDto);

        superHero = createSuperHeroUseCase.apply(superHero);
        return new ResponseEntity<>(superHeroRestMapper.toResponseDto(superHero), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Superhero")
    @ExecutionTime
    public ResponseEntity<SuperHeroResponseDto> updateSuperHeroHandler(
            @PathVariable Long id,
            @RequestBody @Valid SuperHeroRequestDto superHeroRequestDto) {
        SuperHero superHero = superHeroRestMapper.toModel(superHeroRequestDto);
        superHero.setId(id);
        superHero = updateSuperHeroUseCase.apply(superHero);
        return new ResponseEntity<>(superHeroRestMapper.toResponseDto(superHero), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Superhero")
    @ExecutionTime
    public void deleteSuperHeroHandler(
            @PathVariable Long id){
        deleteSuperHeroUseCase.apply(id);
    }
}
