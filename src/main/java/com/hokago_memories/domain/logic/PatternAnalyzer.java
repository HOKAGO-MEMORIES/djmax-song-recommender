package com.hokago_memories.domain.logic;

import com.hokago_memories.domain.song.Song;
import com.hokago_memories.dto.NewSongRecommendation;
import java.util.List;

public class PatternAnalyzer {

    private final DjPowerCalculator djPowerCalculator;
    private final TargetAccuracyCalculator targetAccuracyCalculator;

    public PatternAnalyzer(DjPowerCalculator djPowerCalculator, TargetAccuracyCalculator targetAccuracyCalculator) {
        this.djPowerCalculator = djPowerCalculator;
        this.targetAccuracyCalculator = targetAccuracyCalculator;
    }

    public void analyzePattern(List<NewSongRecommendation> list, Song song, int button,
                               String difficulty, double cutoff, String categoryName) {
        int level = SongPatternFinder.findLevel(song, button, difficulty);

        if (level == 0) {
            return;
        }

        // 99.5%로 쳤을 때 커트라인을 넘는지 확인
        double testScore = 99.5;
        double expectedPower = djPowerCalculator.calculate(difficulty, level, testScore);

        if (expectedPower > cutoff) {
            double requiredAcc = targetAccuracyCalculator.findRequiredAccuracy(difficulty, level, cutoff + 0.1);

            if (requiredAcc > 0 && requiredAcc <= 99.5) {
                list.add(new NewSongRecommendation(
                        song.name(),
                        categoryName,
                        difficulty,
                        level,
                        requiredAcc,
                        expectedPower,
                        expectedPower - cutoff
                ));
            }
        }
    }
}
