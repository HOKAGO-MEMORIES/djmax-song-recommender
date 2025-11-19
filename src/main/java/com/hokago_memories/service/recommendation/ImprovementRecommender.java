package com.hokago_memories.service.recommendation;

import com.hokago_memories.domain.calculator.DjPowerCalculator;
import com.hokago_memories.domain.song.Song;
import com.hokago_memories.domain.util.SongPatternFinder;
import com.hokago_memories.dto.internal.PlayRecordDto;
import com.hokago_memories.dto.response.ImprovementRecommendation;
import com.hokago_memories.repository.SongRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ImprovementRecommender {

    private final SongRepository songRepository;
    private final DjPowerCalculator djPowerCalculator;

    public ImprovementRecommender(SongRepository songRepository, DjPowerCalculator djPowerCalculator) {
        this.songRepository = songRepository;
        this.djPowerCalculator = djPowerCalculator;
    }

    public List<ImprovementRecommendation> recommend(List<PlayRecordDto> targets, int button) {
        List<ImprovementRecommendation> recommendations = new ArrayList<>();

        for (PlayRecordDto record : targets) {
            Song song = songRepository.findByTitle(record.title()).orElse(null);
            if (song == null) {
                continue;
            }

            int level = SongPatternFinder.findLevel(song, button, record.pattern());
            if (level == 0) {
                continue;
            }

            double currentScore = record.score();

            if (record.djpower() <= 0) {
                continue;
            }

            // 현재 점수 + 1.0% (최대 100.0%)
            double targetScore = Math.min(currentScore + 1.0, 100.0);
            double expectedPower = djPowerCalculator.calculate(record.pattern(), level, targetScore);
            double increase = expectedPower - record.djpower();

            // 상승폭이 0.5점 이상
            if (increase > 0.5) {
                recommendations.add(new ImprovementRecommendation(
                        song.name(),
                        record.pattern(),
                        level,
                        currentScore,
                        record.djpower(),
                        targetScore,
                        expectedPower,
                        increase
                ));
            }
        }

        return recommendations.stream()
                .sorted(Comparator.comparingDouble(ImprovementRecommendation::powerIncrease).reversed())
                .limit(10)
                .toList();
    }
}
