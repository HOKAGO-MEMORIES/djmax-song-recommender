package com.hokago_memories.view;

import com.hokago_memories.exception.ErrorMessage;
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
        validateListSize(inputs);
        validateNickname(inputs.get(0));
        validateButton(inputs.get(1));
    }

    private static void validateEmptyOrNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(
                    ErrorMessage.EMPTY_OR_NULL.getErrorMessage()
            );
        }
    }

    private static void validateListSize(List<String> inputs) {
        if (inputs.size() != CORRECT_SIZE) {
            throw new IllegalArgumentException(
                    ErrorMessage.INVALID_LIST_SIZE.getErrorMessage()
            );
        }
    }

    private static void validateNickname(String input) {
        validatePattern(input, NICKNAME_PATTERN, ErrorMessage.INVALID_NICKNAME.getErrorMessage());
    }

    private static void validateButton(String input) {
        validatePattern(input, BUTTON_PATTERN, ErrorMessage.INVALID_BUTTON.getErrorMessage());
    }

    private static void validatePattern(String input, Pattern pattern, String error) {
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(error);
        }
    }
}
