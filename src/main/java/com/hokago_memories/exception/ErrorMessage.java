package com.hokago_memories.exception;

public enum ErrorMessage {
    EMPTY_OR_NULL("빈 값이 입력되었습니다. 문자열을 입력해주세요."),
    INVALID_LIST_SIZE("입력되지 않은 값이 있습니다.\n올바른 입력 형식은 '(닉네임),(버튼수)' 입니다"),
    INVALID_NICKNAME("닉네임 입력 형식이 올바르지 않습니다.\n올바른 입력 형식은 '띄어쓰기 없는 한글영어숫자_-' 2~20자 입니다."),
    INVALID_BUTTON("버튼 수 입력 형식이 올바르지 않습니다.\n버튼 수는 '4, 5, 6, 8'만 입력 가능합니다.");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage(Object... args) {
        return String.format(message, args);
    }
}
