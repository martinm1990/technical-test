package com.maturanomartin.technicaltest.infrastructure.adapter.input.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SuperHeroResponseDto {
    private Long id;
    private String name;
    private int age;
    private String skill;
}
