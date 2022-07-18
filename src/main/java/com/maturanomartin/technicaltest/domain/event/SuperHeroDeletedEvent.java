package com.maturanomartin.technicaltest.domain.event;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SuperHeroDeletedEvent {

    private Long id;
    private LocalDateTime date;

    public SuperHeroDeletedEvent(Long id){
        this.id = id;
        this.date = LocalDateTime.now();
    }

}
