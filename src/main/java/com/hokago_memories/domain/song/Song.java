package com.hokago_memories.domain.song;

public record Song(
        int title,
        String name,
        String composer,
        String dlcCode,
        String dlc,
        Patterns patterns
) {
}
