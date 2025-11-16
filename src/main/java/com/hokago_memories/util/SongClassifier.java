package com.hokago_memories.util;

import com.hokago_memories.domain.PlayRecordDto;
import com.hokago_memories.domain.song.Song;

public class SongClassifier {

    private SongClassifier() {
    }

    public static boolean isNewCategory(Song song) {
        boolean isCpDlc = Constants.CLEAR_PASS.equals(song.dlcCode());
        boolean isNewTitle = song.title() >= Constants.NEW_CATEGORY_TITLE;

        return isCpDlc || isNewTitle;
    }

    public static boolean isNewCategory(PlayRecordDto record) {
        boolean isCpDlc = Constants.CLEAR_PASS.equals(record.dlcCode());
        boolean isNewTitle = record.title() >= Constants.NEW_CATEGORY_TITLE;

        return isCpDlc || isNewTitle;
    }
}
