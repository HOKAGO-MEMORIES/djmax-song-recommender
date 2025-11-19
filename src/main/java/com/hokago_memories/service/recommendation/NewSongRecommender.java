package com.hokago_memories.service.recommendation;

import com.hokago_memories.domain.rule.SongClassifier;
import com.hokago_memories.domain.rule.SongPartitioner;
import com.hokago_memories.domain.song.Song;
import com.hokago_memories.domain.util.SongPatternFinder;
import com.hokago_memories.domain.validator.FeasibilityValidator;
import com.hokago_memories.domain.validator.PatternAnalyzer;
import com.hokago_memories.dto.internal.PlayRecordDto;
import com.hokago_memories.dto.internal.TopRecords;
import com.hokago_memories.dto.response.NewSongRecommendation;
import com.hokago_memories.repository.SongRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NewSongRecommender {

    private final SongRepository songRepository;
    private final PatternAnalyzer patternAnalyzer;


    public NewSongRecommender(SongRepository songRepository, PatternAnalyzer patternAnalyzer) {
        this.songRepository = songRepository;
        this.patternAnalyzer = patternAnalyzer;
    }

    public List<NewSongRecommendation> recommend(List<PlayRecordDto> currentRecords, int button) {
        TopRecords<PlayRecordDto> topRecords = SongPartitioner.selectTopRecords(
                currentRecords,
                SongClassifier::isNewCategory,
                PlayRecordDto::djpower
        );

        List<PlayRecordDto> top50 = topRecords.topBasic().stream().limit(50).toList();
        double averageFloor = FeasibilityValidator.calculateAverageFloor(top50);

        double basicCutoff = getLowestScore(topRecords.topBasic(), 70);
        double newCutoff = getLowestScore(topRecords.topNew(), 30);

        Set<Integer> playedTitles = currentRecords.stream().map(PlayRecordDto::title).collect(Collectors.toSet());
        List<NewSongRecommendation> recommendations = new ArrayList<>();

        for (Song song : songRepository.findAll()) {
            if (playedTitles.contains(song.title())) {
                continue;
            }

            double songMaxFloor = SongPatternFinder.getSongMaxFloor(song, button);

            if (!FeasibilityValidator.isPlayable(averageFloor, songMaxFloor)) {
                continue;
            }

            boolean isNew = SongClassifier.isNewCategory(song);
            double targetCutoff = isNew ? newCutoff : basicCutoff;
            String categoryName = isNew ? "NEW" : "BASIC";

            patternAnalyzer.analyzePattern(recommendations, song, button, "SC", targetCutoff, categoryName);
            patternAnalyzer.analyzePattern(recommendations, song, button, "MX", targetCutoff, categoryName);
        }

        return recommendations.stream()
                .sorted(Comparator.comparingDouble(NewSongRecommendation::expectedDjPower).reversed())
                .limit(10)
                .toList();
    }

    private double getLowestScore(List<PlayRecordDto> list, int limit) {
        if (list.size() < limit) {
            return 0.0;
        }
        return list.get(list.size() - 1).djpower();
    }
}
