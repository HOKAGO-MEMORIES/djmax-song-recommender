package com.hokago_memories.domain.song;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record Patterns(
        @Embedded @JsonProperty("4B") PatternButton b4,
        @Embedded @JsonProperty("5B") PatternButton b5,
        @Embedded @JsonProperty("6B") PatternButton b6,
        @Embedded @JsonProperty("8B") PatternButton b8
) {
}
