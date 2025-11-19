package com.hokago_memories.domain.logic;

import com.hokago_memories.dto.PlayRecordDto;
import java.util.List;

public class FeasibilityValidator {

    // 평균 층수에서 +- 1.0층 이상 벗어나는 곡 제외
    private static final double FLOOR_RANGE_UPPER = 1.0;
    private static final double FLOOR_RANGE_LOWER = 1.0;

    public static boolean isPlayable(double userAverageFloor, double songMaxFloor) {
        if (songMaxFloor <= 0) {
            return false;
        }

        return songMaxFloor <= userAverageFloor + FLOOR_RANGE_UPPER
                && songMaxFloor >= userAverageFloor - FLOOR_RANGE_LOWER;
    }

    public static double calculateAverageFloor(List<PlayRecordDto> top50) {
        return top50.stream()
                .filter(r -> r.scFloor() != null)
                .mapToDouble(PlayRecordDto::scFloor)
                .average()
                .orElse(0.0);
    }
}
