package com.hokago_memories.service.impl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TierInfo(
        @JsonProperty("name") String name
) {
}
