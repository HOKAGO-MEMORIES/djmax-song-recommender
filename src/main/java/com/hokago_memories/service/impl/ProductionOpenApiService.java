package com.hokago_memories.service.impl;

import com.hokago_memories.domain.PlayRecordDto;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.domain.dto.BoardDto;
import com.hokago_memories.domain.dto.TierDto;
import com.hokago_memories.exception.ErrorMessage;
import com.hokago_memories.exception.TierNotFoundException;
import com.hokago_memories.exception.UserNotFoundException;
import com.hokago_memories.service.OpenApiService;
import com.hokago_memories.util.Constants;
import com.hokago_memories.util.NetworkClient;
import com.hokago_memories.util.parser.JsonParser;
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

                    if (!jsonResponse.contains("\"success\": true")) {
                        return Stream.<PlayRecordDto>empty();
                    }

                    BoardDto boardDto = JsonParser.parse(jsonResponse, BoardDto.class);

                    return boardDto.floors().stream()
                            .flatMap(floor -> floor.patterns().stream()
                                    .map(p -> new PlayRecordDto(p.title(), p.dlcCode(), p.pattern(), p.djpower()))
                            );
                })
                .toList();
    }
}
