package com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence;

import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.entity.SuperHeroEntity;
import com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.mapper.SuperHeroPersistenceMapper;
import com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.repository.SuperHeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperHeroPersistenceAdapter implements SuperHeroOutputPort {

    private final SuperHeroPersistenceMapper superHeroPersistenceMapper;
    private final SuperHeroRepository superHeroRepository;

    @Override
    public List<SuperHero> getAllSuperHero() {
        List<SuperHeroEntity> superHeroes = superHeroRepository.findAll();
        return superHeroPersistenceMapper.toListModel(superHeroes);
    }

    @Override
    public List<SuperHero> getAllSuperHeroByName(String name) {
        List<SuperHeroEntity> superHeroes = superHeroRepository.findByNameContainsIgnoreCase(name);
        return superHeroPersistenceMapper.toListModel(superHeroes);
    }

}
