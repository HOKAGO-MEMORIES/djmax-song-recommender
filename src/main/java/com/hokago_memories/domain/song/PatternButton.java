package com.hokago_memories.domain.song;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record PatternButton(
        @Embedded @JsonProperty("NM") PatternDifficulty NM,
        @Embedded @JsonProperty("HD") PatternDifficulty HD,
        @Embedded @JsonProperty("MX") PatternDifficulty MX,
        @Embedded @JsonProperty("SC") PatternDifficulty SC
) {
}
