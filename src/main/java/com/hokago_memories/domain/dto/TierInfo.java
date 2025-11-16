package com.hokago_memories.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TierInfo(
        @JsonProperty("name") String name
) {
}
