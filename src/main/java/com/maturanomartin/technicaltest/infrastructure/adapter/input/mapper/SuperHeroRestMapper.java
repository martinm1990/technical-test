package com.maturanomartin.technicaltest.infrastructure.adapter.input.mapper;

import com.maturanomartin.technicaltest.domain.model.SuperHero;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.dto.SuperHeroRequestDto;
import com.maturanomartin.technicaltest.infrastructure.adapter.input.dto.SuperHeroResponseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface SuperHeroRestMapper {

    SuperHero toModel(SuperHeroRequestDto superHeroRequestDto);
    SuperHeroResponseDto toResponseDto(SuperHero superHero);
    List<SuperHeroResponseDto> toResponseListDto(List<SuperHero> superHero);

}
