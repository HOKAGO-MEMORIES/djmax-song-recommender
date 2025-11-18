package com.hokago_memories.dto;

import com.hokago_memories.domain.Categorizable;

public record PlayRecordDto(
        Integer title,
        String dlcCode,
        String pattern,
        Double score,
        Double djpower
) implements Categorizable {
}
