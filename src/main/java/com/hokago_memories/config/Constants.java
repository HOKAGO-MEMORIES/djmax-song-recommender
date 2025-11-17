package com.hokago_memories.config;

import java.util.List;

public final class Constants {

    // NEW 태그 title 번호
    public static final int NEW_CATEGORY_TITLE_MIN = 687;
    public static final int NEW_CATEGORY_TITLE_MAX = 750;

    // 클리어패스 대상곡
    public static final String CLEAR_PASS = "CP";

    // 성과표 조회 boards
    public static final List<String> BOARDS =
            List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "MX", "SC");

    // CP에서 NEW 태그 제외 음악
    public static final int CP_EXCLUSION_TITLE = 685;
}
