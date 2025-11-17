package service;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.logic.DjClassCalculator;
import com.hokago_memories.dto.PlayRecordDto;
import com.hokago_memories.dto.TheoreticalMax;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DjClassCalculatorTest {

    private DjClassCalculator calculator;

    @BeforeEach
    void setup() {
        TheoreticalMax theoreticalMax = new TheoreticalMax(8000.0, 0.0, 0.0, 0.0);
        this.calculator = new DjClassCalculator(theoreticalMax);
    }

    @DisplayName("NEW 1곡, BASIC 2곡으로 DJCLASS를 계산한다.")
    @Test
    void calculate_with_sampleList() {
        List<PlayRecordDto> records = List.of(
                new PlayRecordDto(179, "VE", "MX", 57.6108),
                new PlayRecordDto(631, "VL", "HD", 60.8395),
                new PlayRecordDto(738, "ARC", "MX", 62.2297)
        );

        DjClass result = calculator.calculate(records, 4);

        Assertions.assertThat(result.basicSum()).isEqualTo(118.4503);
        Assertions.assertThat(result.newSum()).isEqualTo(62.2297);
        Assertions.assertThat(result.rawTotal()).isEqualTo(180.68);
        Assertions.assertThat(result.score()).isEqualTo(225.85);
    }
}