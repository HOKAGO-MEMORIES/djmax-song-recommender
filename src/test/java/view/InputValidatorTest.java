package view;

import com.hokago_memories.view.InputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {

    @DisplayName("입력값으로 빈 문자열이나 null값을 입력할 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validate_emptyOrNull(String input) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("형식에 맞지 않는 닉네임을 입력할 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {" ,4", "닉 네임,4", "pobi!,5", "가,4", "김수한무거북이와두루미삼천갑자동방삭치치카포사리사리센타워리워리세브리깡,8"})
    void validate_invalidNickname(String input) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("형식에 맞지 않는 버튼수를 입력할 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"유저1,1", "유저2,9", "유저3,100", "유저4,"})
    void validate_invalidButton(String input) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
