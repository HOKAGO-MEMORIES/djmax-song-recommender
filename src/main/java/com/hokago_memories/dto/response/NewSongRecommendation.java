package com.hokago_memories.dto.response;

public record NewSongRecommendation(
        String title,
        String category,
        String difficulty,
        int level,
        double targetScore,
        double expectedDjPower,
        double gapOverCutoff
) {
    public String toStringFormat() {
        return String.format("[%s] %s (%s %d) - 목표: %.2f%% 달성 시 진입 가능 (예상 +%.2f점)",
                category, title, difficulty, level, targetScore, gapOverCutoff);
    }
}
