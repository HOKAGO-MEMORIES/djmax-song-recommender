package com.hokago_memories.domain.logic;

import com.hokago_memories.domain.song.PatternButton;
import com.hokago_memories.domain.song.PatternDifficulty;
import com.hokago_memories.domain.song.Song;

public class SongPatternFinder {

    public static int findLevel(Song song, int button, String difficulty) {
        PatternButton patternButton = getPatternButton(song, button);
        if (patternButton == null) {
            return 0;
        }

        PatternDifficulty pat = switch (difficulty) {
            case "SC" -> patternButton.SC();
            case "MX" -> patternButton.MX();
            case "HD" -> patternButton.HD();
            case "NM" -> patternButton.NM();
            default -> null;
        };
        return pat != null ? pat.level() : 0;
    }

    public static double getSongMaxFloor(Song song, int button) {
        PatternButton patternButton = getPatternButton(song, button);

        if (patternButton == null) {
            return 0.0;
        }

        double maxFloor = 0.0;

        if (patternButton.SC() != null && patternButton.SC().floor() != null) {
            maxFloor = Math.max(maxFloor, patternButton.SC().floor());
        }

        if (patternButton.MX() != null && patternButton.MX().floor() != null) {
            maxFloor = Math.max(maxFloor, patternButton.MX().floor());
        }

        return maxFloor;
    }

    private static PatternButton getPatternButton(Song song, int button) {
        return switch (button) {
            case 4 -> song.patterns().b4();
            case 5 -> song.patterns().b5();
            case 6 -> song.patterns().b6();
            case 8 -> song.patterns().b8();
            default -> null;
        };
    }
}
