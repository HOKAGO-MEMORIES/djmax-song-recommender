package com.hokago_memories.domain.dto;

import java.util.List;

public record FloorDto(
        double floorNumber,
        List<PatternDto> patterns
) {
}
