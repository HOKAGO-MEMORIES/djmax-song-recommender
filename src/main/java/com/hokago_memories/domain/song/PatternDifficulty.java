package com.hokago_memories.domain.song;

import jakarta.persistence.Embeddable;

@Embeddable
public record PatternDifficulty(
        int level,
        Double floor,
        Double rating
) {
}
