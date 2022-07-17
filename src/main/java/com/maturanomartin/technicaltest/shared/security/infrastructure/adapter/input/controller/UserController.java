package com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.input.controller;

import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.input.dto.TokenResponseDto;
import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.input.dto.UserRequestDto;
import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.CustomUserDetailsService;
import com.maturanomartin.technicaltest.shared.security.infrastructure.adapter.output.JwtUtilService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ControllerAdvice
@RequestMapping("/auth")
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilService jwtUtilService;

    public UserController(CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager, JwtUtilService jwtUtilService) {
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtilService = jwtUtilService;
    }

    @PostMapping()
    public ResponseEntity<TokenResponseDto> getTokenHandler(@RequestBody @Valid UserRequestDto userRequestDto){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userRequestDto.getUser(), userRequestDto.getPassword())
        );

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userRequestDto.getUser());
        final String jwt = jwtUtilService.generateToken(userDetails);

        TokenResponseDto tokenResponseDto = new TokenResponseDto(jwt);

        return ResponseEntity.ok(tokenResponseDto);
    }
}
