package com.hokago_memories.service.impl.dto;

import java.util.List;

public record BoardDto(
        boolean success,
        List<FloorDto> floors
) {
}
