package service;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.PlayRecordDto;
import com.hokago_memories.service.DjClassCalculator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DjClassCalculatorTest {

    private DjClassCalculator calculator;

    @BeforeEach
    void setup() {
        double theoreticalMax = 8000.0;
        this.calculator = new DjClassCalculator(theoreticalMax);
    }

    @DisplayName("NEW 1곡, BASIC 2곡으로 DJCLASS를 계산한다.")
    @Test
    void calculate_with_sampleList() {
        List<PlayRecordDto> records = List.of(
                new PlayRecordDto(179, "Lisrim", "onoken", "MX", 2.1, 99.34, 57.6108, 133.739),
                new PlayRecordDto(631, "Diomedes", "XeoN", "HD", 5.3, 99.10, 60.8395, 139.546),
                new PlayRecordDto(738, "Fracture Ray", "Sakuzyo", "MX", 5.3, 99.41, 62.2297, 143.192)
        );

        DjClass result = calculator.calculate(records);

        Assertions.assertThat(result.basicSum()).isEqualTo(118.4503);
        Assertions.assertThat(result.newSum()).isEqualTo(62.2297);
        Assertions.assertThat(result.rawTotal()).isEqualTo(180.68);
        Assertions.assertThat(result.score()).isEqualTo(225.85);
    }
}