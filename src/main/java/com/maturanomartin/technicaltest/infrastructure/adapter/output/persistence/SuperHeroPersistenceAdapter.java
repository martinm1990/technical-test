package com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence;

import com.maturanomartin.technicaltest.application.ports.output.SuperHeroOutputPort;
import com.maturanomartin.technicaltest.domain.model.SuperHero;
import com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.entity.SuperHeroEntity;
import com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.mapper.SuperHeroPersistenceMapper;
import com.maturanomartin.technicaltest.infrastructure.adapter.output.persistence.repository.SuperHeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public SuperHero save(SuperHero superHero) {
        SuperHeroEntity superHeroEntity = superHeroPersistenceMapper.toEntity(superHero);
        superHeroEntity = superHeroRepository.save(superHeroEntity);
        return superHeroPersistenceMapper.toModel(superHeroEntity);
    }

    @Override
    @Cacheable(cacheNames = "superheroes", key="#id")
    public Optional<SuperHero> getSuperHeroById(Long id) {

        Optional<SuperHeroEntity> superHeroOptional = superHeroRepository.findById(id);

        if (superHeroOptional.isEmpty()) {
            return Optional.empty();
        }

        SuperHero superHero = superHeroPersistenceMapper.toModel(superHeroOptional.get());

        return Optional.of(superHero);
    }

    @Override
    @CacheEvict(cacheNames = "superheroes", key = "#superHero.id")
    public void delete(SuperHero superHero) {
        SuperHeroEntity superHeroEntity = superHeroPersistenceMapper.toEntity(superHero);
        superHeroRepository.delete(superHeroEntity);
    }
}
