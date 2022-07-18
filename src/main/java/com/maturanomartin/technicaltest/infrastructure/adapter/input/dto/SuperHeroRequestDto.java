package com.maturanomartin.technicaltest.infrastructure.adapter.input.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SuperHeroRequestDto {
    @NotEmpty(message = "Name is required")
    private String name;
    @NotNull(message = "Age is required")
    private int age;
    @NotEmpty(message = "Skill is required")
    private String skill;
}
