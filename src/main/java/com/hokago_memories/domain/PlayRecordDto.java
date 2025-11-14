package com.hokago_memories.domain;

public record PlayRecordDto(
        int title,
        String name,
        String composer,
        String pattern,
        double floorNumber,
        double score,
        double djpower,
        double rating
) {
}
