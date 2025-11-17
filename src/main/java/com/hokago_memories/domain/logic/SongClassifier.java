package com.hokago_memories.domain.logic;

import com.hokago_memories.config.Constants;
import com.hokago_memories.domain.Categorizable;

public class SongClassifier {

    private SongClassifier() {
    }

    public static <T extends Categorizable> boolean isNewCategory(T item) {
        if (item.title() == null) {
            return false;
        }

        int title = item.title();
        if (title >= Constants.NEW_CATEGORY_TITLE_MIN &&
                title <= Constants.NEW_CATEGORY_TITLE_MAX) {
            return true;
        }

        if (Constants.CLEAR_PASS.equals(item.dlcCode())) {
            if (title == Constants.CP_EXCLUSION_TITLE) {
                return false;
            }
            return true;
        }

        return false;
    }
}
