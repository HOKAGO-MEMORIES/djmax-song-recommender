package com.hokago_memories.dto.internal;

import com.hokago_memories.domain.util.Categorizable;

public record PlayRecordDto(
        Integer title,
        String dlcCode,
        String pattern,
        Double scFloor,
        Double score,
        Double djpower
) implements Categorizable {
}
