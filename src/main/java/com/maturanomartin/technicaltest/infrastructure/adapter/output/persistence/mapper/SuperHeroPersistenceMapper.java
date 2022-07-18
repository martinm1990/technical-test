package com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.mapper;

import com.maturanomartin.technicaltest.domain.model.SuperHero;
import com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.entity.SuperHeroEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SuperHeroPersistenceMapper {
    SuperHeroEntity toEntity(SuperHero superHero);

    SuperHero toModel(SuperHeroEntity superHeroEntity);

    List<SuperHero> toListModel(List<SuperHeroEntity> superHeroes);
}
