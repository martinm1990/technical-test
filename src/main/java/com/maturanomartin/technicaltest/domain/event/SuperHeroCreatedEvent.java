package com.maturanomartin.technicaltest.domain.event;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SuperHeroCreatedEvent {

    private Long id;
    private LocalDateTime date;

    public SuperHeroCreatedEvent(Long id){
        this.id = id;
        this.date = LocalDateTime.now();
    }

}
