package com.maturanomartin.technicaltest.shared.security.application.ports.input;

import com.maturanomartin.technicaltest.shared.security.domain.model.User;

@FunctionalInterface
public interface GetUserByUsername {

    User apply(String username);

}
