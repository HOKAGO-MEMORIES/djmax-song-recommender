package com.hokago_memories.service.api.dto;

import java.util.List;

public record FloorDto(
        Double floorNumber,
        List<PatternDto> patterns
) {
}
