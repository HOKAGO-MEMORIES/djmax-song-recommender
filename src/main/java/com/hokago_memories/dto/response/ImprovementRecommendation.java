package com.hokago_memories.dto.response;

public record ImprovementRecommendation(
        String title,
        String difficulty,
        int level,
        double currentScore,
        double currentDjPower,
        double targetScore,
        double targetDjPower,
        double powerIncrease
) {
    public String toStringFormat() {
        return String.format("[%s %d] %.2f%% -> %.2f%% (DJCLASS +%.2f)",
                difficulty, level, currentScore, targetScore, powerIncrease);
    }
}
