package service;

import com.hokago_memories.domain.UserRequest;
import com.hokago_memories.service.OpenApiService;
import com.hokago_memories.service.PlayerInfoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerInfoServiceTest {

    private PlayerInfoService service;

    @BeforeEach
    void setup() {
        OpenApiService openApiService = new StubOpenApiService();
        this.service = new PlayerInfoService(openApiService);
    }

    @DisplayName("등록되어 있지 않은 닉네임을 입력하면 예외처리 한다.")
    @Test
    void findTier_invalidNickname() {
        UserRequest request = new UserRequest("UserNotFound", 4);

        Assertions.assertThatThrownBy(() -> service.getUserTier(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당 버튼에 유저의 티어 정보가 없을 경우 예외처리 한다.")
    @Test
    void findTier_invalidButtonDate() {
        UserRequest request = new UserRequest("ValidNickname", 5);

        Assertions.assertThatThrownBy(() -> service.getUserTier(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 티어 데이터를 입력하면 Tier 객체를 반환한다.")
    @Test
    void findTier_validData() {
        UserRequest request = new UserRequest("ValidNickname", 4);

        Assertions.assertThat(service.getUserTier(request).tierName())
                .isEqualTo("GOLD III");
    }
}
