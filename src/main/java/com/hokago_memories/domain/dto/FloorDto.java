package com.hokago_memories.domain.dto;

import java.util.List;

public record FloorDto(
        Double floorNumber,
        List<PatternDto> patterns
) {
}
