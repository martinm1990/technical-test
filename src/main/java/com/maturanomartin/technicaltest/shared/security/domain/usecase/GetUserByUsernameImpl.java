package com.maturanomartin.technicaltest.shared.security.domain.usecase;

import com.maturanomartin.technicaltest.shared.security.application.ports.input.GetUserByUsername;
import com.maturanomartin.technicaltest.shared.security.application.ports.output.UserOutputPort;
import com.maturanomartin.technicaltest.shared.security.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserByUsernameImpl implements GetUserByUsername {

    private final UserOutputPort userOutputPort;

    @Autowired
    public GetUserByUsernameImpl(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public User apply(String username) {
        return userOutputPort.getUserByUserName(username);
    }
}
