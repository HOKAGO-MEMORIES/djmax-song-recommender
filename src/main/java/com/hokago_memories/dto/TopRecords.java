package com.hokago_memories.dto;

import java.util.List;

public record TopRecords<T>(
        List<T> topNew,
        List<T> topBasic
) {
}
