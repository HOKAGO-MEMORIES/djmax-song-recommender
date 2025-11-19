package com.hokago_memories.domain.rule;

import com.hokago_memories.config.Constants;
import com.hokago_memories.domain.util.Categorizable;

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
            return title != Constants.CP_EXCLUSION_TITLE;
        }

        return false;
    }
}
