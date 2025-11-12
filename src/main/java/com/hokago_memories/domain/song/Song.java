package com.hokago_memories.domain.song;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record Song(
        @Id int title,
        String name,
        String composer,
        String dlcCode,
        String dlc,
        @Embedded Patterns patterns
) {
}
