package com.hokago_memories.domain.dto;

import java.util.List;

public record BoardDto(
        boolean success,
        List<FloorDto> floors
) {
}
