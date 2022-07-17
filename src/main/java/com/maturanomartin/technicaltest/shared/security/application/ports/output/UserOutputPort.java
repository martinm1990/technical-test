package com.maturanomartin.technicaltest.shared.security.application.ports.output;

import com.maturanomartin.technicaltest.shared.security.domain.model.User;

public interface UserOutputPort {

    User getUserByUserName(String username);

}
