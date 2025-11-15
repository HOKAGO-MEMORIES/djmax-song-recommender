package com.hokago_memories.domain;

public record DjClass(
        String displayName,
        double score,
        double rawTotal,
        double basicSum,
        double newSum
) {
}
