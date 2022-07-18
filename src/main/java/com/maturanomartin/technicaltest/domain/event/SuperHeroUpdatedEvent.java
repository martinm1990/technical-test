package com.maturanomartin.technicaltest.domain.event;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SuperHeroUpdatedEvent {

    private Long id;
    private LocalDateTime date;

    public SuperHeroUpdatedEvent(Long id){
        this.id = id;
        this.date = LocalDateTime.now();
    }

}
