package com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.persistence.repository;

import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<UserEntity, Long> {

    UserEntity findUserEntitiesByUsername(String username);

}
