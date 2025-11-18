package service;

import static org.assertj.core.api.AssertionsForClassTypes.within;

import com.hokago_memories.domain.logic.DjPowerCalculator;
import com.hokago_memories.domain.logic.DjPowerRules;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DjPowerCalculatorTest {

    private DjPowerCalculator calculator;

    @BeforeEach
    void setup() {
        DjPowerRules djPowerRules = new DjPowerRules();
        this.calculator = new DjPowerCalculator(djPowerRules);
    }

    @DisplayName("정확도가 90% 미만이면 DJPOWER로 0을 반환한다.")
    @Test
    void calculate_under90_returnZero() {
        double score = 89.99;
        String difficulty = "SC";
        int level = 15;

        double result = calculator.calculate(difficulty, level, score);

        Assertions.assertThat(result).isEqualTo(0.0);
    }

    @DisplayName("정확도가 100%면 최대 DJPOWER를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "SC, 15, 99.99",
            "SC, 1, 53.37",
            "MX, 15, 68.91",
            "NM, 1, 6.75"
    })
    void calculate_100Percent_returnMaxDjPower(String difficulty, int level, double expectedMax) {
        double score = 100.0;

        double result = calculator.calculate(difficulty, level, score);

        Assertions.assertThat(result).isCloseTo(expectedMax, within(0.001));
    }


    @DisplayName("SC 15레벨 99.0%일 때의 DJPOWER 계산")
    @Test
    void calculate_djpower() {
        double score = 99.0;
        String difficulty = "SC";
        int level = 15;

        double result = calculator.calculate(difficulty, level, score);

        Assertions.assertThat(result).isCloseTo(93.37, within(0.1));

        System.out.println("Calculated DJ POWER for 99.0%: " + result);
    }
}
