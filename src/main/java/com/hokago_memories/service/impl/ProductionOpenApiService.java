package com.hokago_memories.service.impl;

import com.hokago_memories.config.Constants;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.dto.PlayRecordDto;
import com.hokago_memories.exception.ErrorMessage;
import com.hokago_memories.exception.TierNotFoundException;
import com.hokago_memories.exception.UserNotFoundException;
import com.hokago_memories.infrastructure.api.NetworkClient;
import com.hokago_memories.infrastructure.parser.JsonParser;
import com.hokago_memories.service.OpenApiService;
import com.hokago_memories.service.impl.dto.BoardDto;
import com.hokago_memories.service.impl.dto.FloorDto;
import com.hokago_memories.service.impl.dto.PatternDto;
import com.hokago_memories.service.impl.dto.TierDto;
import java.util.List;
import java.util.stream.Stream;

public class ProductionOpenApiService implements OpenApiService {

    private final NetworkClient networkClient;

    private static final String API_BASE_URL = "https://v-archive.net/api/archive";

    public ProductionOpenApiService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    // GET /api/archive/{nickname}/tier/{button}
    @Override
    public Tier findTier(String nickname, int button) {
        String url = String.format("%s/%s/tier/%d", API_BASE_URL, nickname, button);

        String jsonResponse = networkClient.get(url);

        if (jsonResponse.contains("\"errorCode\": 101")) {
            throw new UserNotFoundException(ErrorMessage.USER_NOT_FOUND.getErrorMessage(nickname));
        }
        if (jsonResponse.contains("\"errorCode\": 111")) {
            throw new TierNotFoundException(ErrorMessage.TIER_NOT_FOUND.getErrorMessage(button));
        }

        TierDto dto = JsonParser.parse(jsonResponse, TierDto.class);
        return new Tier(dto.tier().name(), dto.tierPoint());
    }

    // GET /api/archive/{nickname}/board/{button}/{board}
    @Override
    public List<PlayRecordDto> findDjClassBoard(String nickname, int button) {
        List<String> boards = Constants.BOARDS;

        return boards.stream()
                .parallel()
                .flatMap(boardName -> {
                    String url = String.format("%s/%s/board/%d/%s", API_BASE_URL, nickname, button, boardName);
                    String jsonResponse = networkClient.get(url);

                    if (!jsonResponse.contains("\"success\":true")) {
                        return Stream.<PlayRecordDto>empty();
                    }

                    try {
                        BoardDto boardDto = JsonParser.parse(jsonResponse, BoardDto.class);

                        List<FloorDto> floors = boardDto.floors();
                        if (floors == null) {
                            return Stream.<PlayRecordDto>empty();
                        }

                        return floors.stream()
                                .flatMap(floor -> {
                                    List<PatternDto> patterns = floor.patterns();
                                    if (patterns == null) {
                                        return Stream.<PlayRecordDto>empty();
                                    }
                                    return patterns.stream()
                                            .filter(p -> p.djpower() != null && p.djpower() > 0)
                                            .map(p -> new PlayRecordDto(p.title(), p.dlcCode(), p.pattern(),
                                                    p.score(), p.djpower()));
                                });
                    } catch (Exception e) {
                        return Stream.<PlayRecordDto>empty();
                    }
                })
                .toList();
    }
}
