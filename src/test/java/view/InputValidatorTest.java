package view;

import com.hokago_memories.view.InputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class InputValidatorTest {

    @DisplayName("입력값으로 빈 문자열이나 null값을 입력할 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validate_emptyOrNull(String input) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
