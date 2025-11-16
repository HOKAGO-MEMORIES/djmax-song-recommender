package com.hokago_memories.domain.song;

import jakarta.persistence.Embeddable;

@Embeddable
public record PatternDifficulty(
        Integer level,
        Double floor,
        Integer rating
) {
}
