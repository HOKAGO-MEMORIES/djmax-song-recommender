package com.hokago_memories.domain.song;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Patterns(
        @JsonProperty("4B") PatternButton b4,
        @JsonProperty("5B") PatternButton b5,
        @JsonProperty("6B") PatternButton b6,
        @JsonProperty("8B") PatternButton b8
) {
}
