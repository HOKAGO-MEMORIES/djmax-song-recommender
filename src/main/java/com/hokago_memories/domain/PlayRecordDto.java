package com.hokago_memories.domain;

public record PlayRecordDto(
        int title,
        String name,
        String composer,
        String dlcCode,
        String dlc,
        String pattern,
        int floorNumber,
        double scFloor,
        double score,
        double djpower,
        double rating
) {
}
