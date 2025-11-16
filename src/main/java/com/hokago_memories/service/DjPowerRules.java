package com.hokago_memories.service;

import java.util.Map;

public class DjPowerRules {

    private static final Map<Integer, Double> DJ_POWER_SC = Map.ofEntries(
            Map.entry(15, 99.99),
            Map.entry(14, 95.55),
            Map.entry(13, 91.11),
            Map.entry(12, 86.67),
            Map.entry(11, 82.23),
            Map.entry(10, 77.79),
            Map.entry(9, 73.35),
            Map.entry(8, 68.91),
            Map.entry(7, 66.69),
            Map.entry(6, 64.47),
            Map.entry(5, 62.25),
            Map.entry(4, 60.03),
            Map.entry(3, 57.81),
            Map.entry(2, 55.59),
            Map.entry(1, 53.37)
    );

    private static final Map<Integer, Double> DJ_POWER_NORMAL = Map.ofEntries(
            Map.entry(15, 68.91),
            Map.entry(14, 64.47),
            Map.entry(13, 60.03),
            Map.entry(12, 55.59),
            Map.entry(11, 51.15),
            Map.entry(10, 46.71),
            Map.entry(9, 42.27),
            Map.entry(8, 37.83),
            Map.entry(7, 33.39),
            Map.entry(6, 28.95),
            Map.entry(5, 24.51),
            Map.entry(4, 20.07),
            Map.entry(3, 15.63),
            Map.entry(2, 11.19),
            Map.entry(1, 6.75)
    );

    public double getMaxPower(String difficulty, int level) {
        int clampedLevel = Math.max(1, Math.min(15, level)); // 1~15 레벨로 고정

        if ("SC".equals(difficulty.toUpperCase())) {
            return DJ_POWER_SC.getOrDefault(clampedLevel, 0.0);
        }

        return DJ_POWER_NORMAL.getOrDefault(clampedLevel, 0.0);
    }
}
