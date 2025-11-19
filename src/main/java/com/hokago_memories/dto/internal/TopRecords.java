package com.hokago_memories.dto.internal;

import java.util.List;

public record TopRecords<T>(
        List<T> topNew,
        List<T> topBasic
) {
}
