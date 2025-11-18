package com.hokago_memories.service;

import com.hokago_memories.domain.logic.DjPowerCalculator;
import com.hokago_memories.domain.logic.SongClassifier;
import com.hokago_memories.domain.logic.SongPartitioner;
import com.hokago_memories.domain.logic.TargetAccuracyCalculator;
import com.hokago_memories.domain.song.PatternButton;
import com.hokago_memories.domain.song.PatternDifficulty;
import com.hokago_memories.domain.song.Song;
import com.hokago_memories.dto.ImprovementRecommendation;
import com.hokago_memories.dto.NewSongRecommendation;
import com.hokago_memories.dto.PlayRecordDto;
import com.hokago_memories.dto.TopRecords;
import com.hokago_memories.dto.UserRequest;
import com.hokago_memories.repository.SongRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecommendationService {

    private final PlayerInfoService playerInfoService;
    private final SongRepository songRepository;
    private final DjPowerCalculator djPowerCalculator;
    private final TargetAccuracyCalculator targetAccuracyCalculator;

    public RecommendationService(
            PlayerInfoService playerInfoService,
            SongRepository songRepository,
            DjPowerCalculator djPowerCalculator,
            TargetAccuracyCalculator targetAccuracyCalculator
    ) {
        this.playerInfoService = playerInfoService;
        this.songRepository = songRepository;
        this.djPowerCalculator = djPowerCalculator;
        this.targetAccuracyCalculator = targetAccuracyCalculator;
    }

    public List<ImprovementRecommendation> recommendImprovements(UserRequest request) {
        List<PlayRecordDto> allRecords = playerInfoService.getRawRecords(request);
        TopRecords<PlayRecordDto> topRecords = SongPartitioner.selectTopRecords(
                allRecords, SongClassifier::isNewCategory, PlayRecordDto::djpower
        );

        List<PlayRecordDto> targets = new ArrayList<>();
        targets.addAll(topRecords.topBasic());
        targets.addAll(topRecords.topNew());

        List<ImprovementRecommendation> recommendations = new ArrayList<>();

        for (PlayRecordDto record : targets) {
            Song song = songRepository.findByTitle(record.title()).orElse(null);
            if (song == null) {
                continue;
            }

            int level = findLevel(song, request.button(), record.pattern());
            if (level == 0) {
                continue;
            }

            double currentScore = record.score();

            if (currentScore > 99.0 || record.djpower() <= 0) {
                continue;
            }

            // 현재 점수 + 1.0% (최대 99.5%)
            double targetScore = Math.min(currentScore + 1.0, 99.5);
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

    public List<NewSongRecommendation> recommendNewSongs(UserRequest request) {
        List<PlayRecordDto> currentRecords = playerInfoService.getRawRecords(request);
        TopRecords<PlayRecordDto> topRecords = SongPartitioner.selectTopRecords(
                currentRecords, SongClassifier::isNewCategory, PlayRecordDto::djpower
        );

        double basicCutoff = getLowestScore(topRecords.topBasic(), 70);
        double newCutoff = getLowestScore(topRecords.topNew(), 30);

        Set<Integer> playedTitles = currentRecords.stream().map(PlayRecordDto::title).collect(Collectors.toSet());
        List<NewSongRecommendation> recommendations = new ArrayList<>();
        List<Song> allSongs = songRepository.findAll();

        for (Song song : allSongs) {
            if (playedTitles.contains(song.title())) {
                continue;
            }

            boolean isNew = SongClassifier.isNewCategory(song);
            double targetCutoff = isNew ? newCutoff : basicCutoff;
            String categoryName = isNew ? "NEW" : "BASIC";

            checkAndAddNewRecommendation(recommendations, song, request.button(), "SC", targetCutoff, categoryName);
            checkAndAddNewRecommendation(recommendations, song, request.button(), "MX", targetCutoff, categoryName);
        }

        return recommendations.stream()
                .sorted(Comparator.comparingDouble(NewSongRecommendation::expectedDjPower).reversed())
                .limit(10)
                .toList();
    }

    private void checkAndAddNewRecommendation(List<NewSongRecommendation> list, Song song, int button,
                                              String difficulty, double cutoff, String categoryName) {
        int level = findLevel(song, button, difficulty);

        if (level == 0) {
            return;
        }

        // 98.0%로 쳤을 때 커트라인을 넘는지 확인
        double testScore = 98.0;
        double expectedPower = djPowerCalculator.calculate(difficulty, level, testScore);

        if (expectedPower > cutoff) {
            double requiredAcc = targetAccuracyCalculator.findRequiredAccuracy(difficulty, level, cutoff + 0.1);

            if (requiredAcc > 0 && requiredAcc <= 99.0) {
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

    private double getLowestScore(List<PlayRecordDto> list, int limit) {
        if (list.size() < limit) {
            return 0.0;
        }
        return list.get(list.size() - 1).djpower();
    }

    private int findLevel(Song song, int button, String difficulty) {
        PatternButton btnPatterns = switch (button) {
            case 4 -> song.patterns().b4();
            case 5 -> song.patterns().b5();
            case 6 -> song.patterns().b6();
            case 8 -> song.patterns().b8();
            default -> null;
        };
        if (btnPatterns == null) {
            return 0;
        }

        PatternDifficulty pat = switch (difficulty) {
            case "SC" -> btnPatterns.SC();
            case "MX" -> btnPatterns.MX();
            case "HD" -> btnPatterns.HD();
            case "NM" -> btnPatterns.NM();
            default -> null;
        };
        return pat != null ? pat.level() : 0;
    }
}
