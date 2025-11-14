package service;

import com.hokago_memories.domain.PlayRecordDto;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.service.OpenApiService;
import java.util.List;

public class StubOpenApiService implements OpenApiService {

    @Override
    public Tier findTier(String nickname, int button) {
        if (nickname.equals("UserNotFound")) {
            throw new IllegalArgumentException("실패 - 닉네임 찾자 못함 (errorCode: 101)");
        }

        if (nickname.equals("ValidNickname") && button == 4) {
            return new Tier("GOLD III", 7600);
        }

        throw new IllegalArgumentException("실패 - 유저의 티어 정보 없음 (errorCode: 111)");
    }

    @Override
    public List<PlayRecordDto> findDjClassBoard(String nickname, int button) {
        return List.of();
    }
}
