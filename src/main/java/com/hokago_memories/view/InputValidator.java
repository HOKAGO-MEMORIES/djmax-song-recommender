package com.hokago_memories.view;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateInput(String input) {
        validateEmptyOrNull(input);
    }

    private static void validateEmptyOrNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(
                    "문자열을 입력해주세요."
            );
        }
    }
}
