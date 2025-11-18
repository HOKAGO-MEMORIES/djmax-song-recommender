package com.hokago_memories.view.output;

public enum OutputMessage {
    START_MESSAGE("닉네임과 버튼 수를 입력해주세요.\n입력 형식은 '(닉네임),(버튼수)' 입니다."),
    TIER_AND_DJCLASS("닉네임: %s\n버튼: %d\n티어: %s\nDJCLASS: %s\n"),
    RECOMMEND_IMPROVEMENT_TITLE("\n========== 기존 기록 갱신 추천 =========="),
    RECOMMEND_IMPROVEMENT_FORMAT(
            "[%s %d] %s\n" +
                    "   └─ 현재: %.2f%% (%.2f점) -> 목표: %.2f%% (예상 +%.2f점)"
    ),
    RECOMMEND_NEW_SONG_TITLE("\n========== 미기록 노래 추천 =========="),
    RECOMMEND_NEW_SONG_FORMAT(
            "[%s] %s (%s %d)\n" +
                    "   └─ 목표 정확도: %.2f%% 달성 시 진입 가능 (예상 %.2f점 / 커트라인 +%.2f점)"
    ),
    NO_RECOMMENDATION("(조건에 맞는 추천 곡을 찾지 못했습니다.)");

    private final String outputMessage;

    OutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public String getOutputMessage(Object... args) {
        return String.format(outputMessage, args);
    }
}
