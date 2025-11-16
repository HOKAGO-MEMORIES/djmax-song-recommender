package service;

import com.hokago_memories.domain.TheoreticalMax;
import com.hokago_memories.repository.SongRepository;
import com.hokago_memories.service.DjPowerRules;
import com.hokago_memories.service.TheoreticalMaxCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.StubSongRepository;

public class TheoreticalMaxCalculatorTest {

    private TheoreticalMaxCalculator calculator;

    @BeforeEach
    void setup() {
        SongRepository stubRepository = new StubSongRepository();
        DjPowerRules rules = new DjPowerRules();
        this.calculator = new TheoreticalMaxCalculator(stubRepository, rules);
    }

    @DisplayName("곡 정보가 주어지면 이론치(상위 난도 패턴을 모두 100% 정확도로 클리어 했을 때 DJPOWER)를 반환한다.")
    @Test
    void calculate_theoreticalMax() {

        // 15(99.99), 14(95.55) (NEW)
        // 15(99.99), 15(99.99) (BASIC)
        double expectedTheoreticalMax = 395.52;
        TheoreticalMax theoreticalMax = calculator.calculateAll();
        double actualTheoreticalMax = theoreticalMax.b4();

        Assertions.assertThat(actualTheoreticalMax).isEqualTo(expectedTheoreticalMax);
        Assertions.assertThat(theoreticalMax.b5()).isEqualTo(0.0);
        Assertions.assertThat(theoreticalMax.b6()).isEqualTo(0.0);
        Assertions.assertThat(theoreticalMax.b8()).isEqualTo(0.0);
    }
}
