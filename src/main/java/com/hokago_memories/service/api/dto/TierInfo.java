package com.hokago_memories.service.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TierInfo(
        @JsonProperty("name") String name
) {
}
