package com.maturanomartin.technicaltest.domain.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public final class SuperHero {
    private Long id;
    private String name;
    private int age;
    private String skill;
}
