package com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.persistence;

import com.maturanomartin.technicaltest.shared.security.application.ports.output.UserOutputPort;
import com.maturanomartin.technicaltest.shared.security.domain.model.User;
import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.persistence.mapper.UserPersistenceMapper;
import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    public UserPersistenceAdapter(UserRepository userRepository, UserPersistenceMapper userPersistenceMapper) {
        this.userRepository = userRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    @Override
    public User getUserByUserName(String username) {
        UserEntity userEntity = userRepository.findUserEntitiesByUsername(username);
        return userPersistenceMapper.mapToModel(userEntity);
    }
}
