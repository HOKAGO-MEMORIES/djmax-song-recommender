package com.hokago_memories.domain;

import java.util.List;

public record TopRecords<T>(
        List<T> topNew,
        List<T> topBasic
) {
}
