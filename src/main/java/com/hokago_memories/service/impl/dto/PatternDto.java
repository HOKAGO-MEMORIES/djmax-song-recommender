package com.hokago_memories.service.impl.dto;

public record PatternDto(
        Integer title,
        String dlcCode,
        String pattern,
        Double scFloor,
        Double score,
        Double djpower
) {
}
