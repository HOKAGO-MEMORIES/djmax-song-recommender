package com.hokago_memories.domain;

public record PlayRecordDto(
        int title,
        String name,
        String composer,
        String dlcCode,
        String dlc,
        String pattern,
        double floorNumber,
        double score,
        double djpower,
        double rating
) {
}
