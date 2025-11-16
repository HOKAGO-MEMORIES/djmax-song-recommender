package service;

import com.hokago_memories.domain.PlayRecordDto;
import com.hokago_memories.domain.Tier;
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
        PlayRecordDto song1 = new PlayRecordDto(729, "STOP", "SAINT MILLER", "PLI2", "PLI : 2025", "SC",
                7, 7.3, 96.16, 330.5815, 124.882);
        PlayRecordDto song2 = new PlayRecordDto(716, "Summer Fling", "Pure 100%", "VL3", "V LIBERTY III", "SC",
                7, 7.1, 96.16, 330.5815, 124.882);
        PlayRecordDto song3 = new PlayRecordDto(484, "별빛정원", "vneld", "TQ", "TECHNIKA T&Q", "SC",
                7, 7.1, 97.65, 500.3644, 131.126);
        PlayRecordDto song4 = new PlayRecordDto(550, "ADDICT!ON (DJMAX Edit)", "Airi Kanna", "VE4", "V EXTENSION 4",
                "SC", 2, 3.2, 99.61, 540.367, 143.382);
        return List.of(song1, song2, song3, song4);
    }
}
