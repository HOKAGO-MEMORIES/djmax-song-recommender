package service;

import com.hokago_memories.domain.Tier;
import com.hokago_memories.dto.PlayRecordDto;
import com.hokago_memories.service.OpenApiService;
import java.util.List;

public class StubOpenApiService implements OpenApiService {

    @Override
    public Tier findTier(String nickname, int button) {
        if (nickname.equals("UserNotFound")) {
            throw new IllegalArgumentException("실패 - 닉네임 찾지 못함 (errorCode: 101)");
        }

        if (nickname.equals("ValidNickname") && button == 4) {
            return new Tier("GOLD III", 7600);
        }

        throw new IllegalArgumentException("실패 - 유저의 티어 정보 없음 (errorCode: 111)");
    }

    @Override
    public List<PlayRecordDto> findDjClassBoard(String nickname, int button) {
        PlayRecordDto song1 = new PlayRecordDto(729, "PLI2", "SC", 330.5815);
        PlayRecordDto song2 = new PlayRecordDto(716, "VL3", "SC", 330.5815);
        PlayRecordDto song3 = new PlayRecordDto(484, "TQ", "SC", 500.3644);
        PlayRecordDto song4 = new PlayRecordDto(550, "VE4", "SC", 540.367);
        return List.of(song1, song2, song3, song4);
    }
}
