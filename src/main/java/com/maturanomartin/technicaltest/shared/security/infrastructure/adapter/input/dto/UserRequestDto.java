package com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.input.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequestDto {

    @NotEmpty(message = "User is required")
    private String user;
    @NotEmpty(message = "Password is required")
    private String password;

}
