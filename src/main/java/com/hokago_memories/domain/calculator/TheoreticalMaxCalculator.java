package com.hokago_memories.domain.calculator;

import com.hokago_memories.domain.rule.DjPowerRules;
import com.hokago_memories.domain.rule.SongClassifier;
import com.hokago_memories.domain.song.PatternButton;
import com.hokago_memories.domain.song.PatternDifficulty;
import com.hokago_memories.domain.song.Song;
import com.hokago_memories.dto.internal.TheoreticalMax;
import com.hokago_memories.repository.SongRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TheoreticalMaxCalculator {

    private final SongRepository songRepository;
    private final DjPowerRules djPowerRules;

    public TheoreticalMaxCalculator(SongRepository songRepository, DjPowerRules djPowerRules) {
        this.songRepository = songRepository;
        this.djPowerRules = djPowerRules;
    }

    public TheoreticalMax calculateAll() {
        List<Song> allSongs = songRepository.findAll();

        Map<String, List<Double>> newPools = Map.of(
                "4B", new ArrayList<>(), "5B", new ArrayList<>(),
                "6B", new ArrayList<>(), "8B", new ArrayList<>()
        );

        Map<String, List<Double>> basicPools = Map.of(
                "4B", new ArrayList<>(), "5B", new ArrayList<>(),
                "6B", new ArrayList<>(), "8B", new ArrayList<>()
        );

        for (Song song : allSongs) {
            if (song.patterns() == null) {
                continue;
            }

            checkAndAddPattern(song, "4B", "MX", newPools, basicPools);
            checkAndAddPattern(song, "4B", "SC", newPools, basicPools);

            checkAndAddPattern(song, "5B", "MX", newPools, basicPools);
            checkAndAddPattern(song, "5B", "SC", newPools, basicPools);

            checkAndAddPattern(song, "6B", "MX", newPools, basicPools);
            checkAndAddPattern(song, "6B", "SC", newPools, basicPools);

            checkAndAddPattern(song, "8B", "MX", newPools, basicPools);
            checkAndAddPattern(song, "8B", "SC", newPools, basicPools);
        }

        double b4Max = sumTopRanked(newPools.get("4B"), 30) + sumTopRanked(basicPools.get("4B"), 70);
        double b5Max = sumTopRanked(newPools.get("5B"), 30) + sumTopRanked(basicPools.get("5B"), 70);
        double b6Max = sumTopRanked(newPools.get("6B"), 30) + sumTopRanked(basicPools.get("6B"), 70);
        double b8Max = sumTopRanked(newPools.get("8B"), 30) + sumTopRanked(basicPools.get("8B"), 70);

        return new TheoreticalMax(b4Max, b5Max, b6Max, b8Max);
    }

    private void checkAndAddPattern(Song song, String buttonKey, String difficultyKey,
                                    Map<String, List<Double>> newPools, Map<String, List<Double>> basicPools) {
        PatternButton button = switch (buttonKey) {
            case "4B" -> song.patterns().b4();
            case "5B" -> song.patterns().b5();
            case "6B" -> song.patterns().b6();
            case "8B" -> song.patterns().b8();
            default -> null;
        };
        if (button == null) {
            return;
        }

        PatternDifficulty pattern = switch (difficultyKey) {
            case "SC" -> button.SC();
            case "MX" -> button.MX();
            default -> null;
        };
        if (pattern == null) {
            return;
        }

        // SC 10~15, MX 15
        int level = pattern.level();
        if ("SC".equals(difficultyKey) && level < 10) {
            return;
        }
        if ("MX".equals(difficultyKey) && level != 15) {
            return;
        }

        double maxPower = djPowerRules.getMaxPower(difficultyKey, level);
        if (maxPower <= 0) {
            return;
        }

        boolean isNew = SongClassifier.isNewCategory(song);
        if (isNew) {
            newPools.get(buttonKey).add(maxPower);
        } else {
            basicPools.get(buttonKey).add(maxPower);
        }
    }

    private double sumTopRanked(List<Double> pool, int limit) {
        return pool.stream()
                .sorted(Comparator.reverseOrder())
                .limit(limit)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
