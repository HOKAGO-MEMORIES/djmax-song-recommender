package com.hokago_memories.view;

import com.hokago_memories.util.Splitter;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[가-힣A-Za-z0-9_-]{2,20}$");
    private static final Pattern BUTTON_PATTERN = Pattern.compile("^([4568])$");
    private static final int CORRECT_SIZE = 2;

    private InputValidator() {
    }

    public static void validateInput(String input) {
        validateEmptyOrNull(input);
        List<String> inputs = Splitter.split(input);
        validateListLength(inputs);
        validateNickname(inputs.get(0));
        validateButton(inputs.get(1));
    }

    private static void validateEmptyOrNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(
                    "문자열을 입력해주세요."
            );
        }
    }

    private static void validateListLength(List<String> inputs) {
        if (inputs.size() != CORRECT_SIZE) {
            throw new IllegalArgumentException(
                    "입력되지 않은 값이 있습니다.\n올바른 입력 형식은 '(닉네임),(버튼수)' 입니다"
            );
        }
    }

    private static void validateNickname(String input) {
        String error = "닉네임 입력 형식이 올바르지 않습니다.\n올바른 입력 형식은 '띄어쓰기 없는 한글영어숫자_-' 2~20자 입니다.";
        validatePattern(input, NICKNAME_PATTERN, error);
    }

    private static void validateButton(String input) {
        String error = "버튼 수 입력 형식이 올바르지 않습니다.\n버튼 수는 '4, 5, 6, 8'만 입력 가능합니다.";
        validatePattern(input, BUTTON_PATTERN, error);
    }

    private static void validatePattern(String input, Pattern pattern, String error) {
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(error);
        }
    }
}
