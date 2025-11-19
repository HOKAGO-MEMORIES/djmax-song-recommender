package com.hokago_memories.service.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TierDto(
        @JsonProperty("tierPoint") double tierPoint,
        @JsonProperty("tier") TierInfo tier
) {
}
