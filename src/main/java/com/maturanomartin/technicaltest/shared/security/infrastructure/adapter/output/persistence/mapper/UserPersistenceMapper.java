package com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.persistence.mapper;

import com.maturanomartin.technicaltest.shared.security.domain.model.User;
import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User mapToModel(UserEntity userEntity);

}
