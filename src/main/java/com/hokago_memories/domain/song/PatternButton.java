package com.hokago_memories.domain.song;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PatternButton(
        @JsonProperty("NM") PatternDifficulty NM,
        @JsonProperty("HD") PatternDifficulty HD,
        @JsonProperty("MX") PatternDifficulty MX,
        @JsonProperty("SC") PatternDifficulty SC
) {
}
