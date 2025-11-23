package com.hokago_memories.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.dto.internal.PlayRecordDto;
import com.hokago_memories.dto.request.UserRequest;
import com.hokago_memories.exception.TierNotFoundException;
import com.hokago_memories.exception.UserNotFoundException;
import com.hokago_memories.service.PlayerInfoService;
import com.hokago_memories.service.recommendation.RecommendationService;
import com.hokago_memories.util.parser.StringParser;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.json.JavalinJackson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebController {

    private final PlayerInfoService playerInfoService;
    private final RecommendationService recommendationService;

    public WebController(PlayerInfoService playerInfoService, RecommendationService recommendationService) {
        this.playerInfoService = playerInfoService;
        this.recommendationService = recommendationService;
    }

    public void start(int port) {
        Javalin app = Javalin.create(config -> {

            // ObjectMapper 설정
            config.jsonMapper(new JavalinJackson().updateMapper(mapper -> {
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                mapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
            }));

            // 2. CORS 설정 (Javalin 6.x 방식)
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });
        }).start(port);

        app.get("/api/analyze", this::analyzeUser);
    }

    private void analyzeUser(Context ctx) {
        try {
            String nickname = ctx.queryParam("nickname");
            String buttonStr = ctx.queryParam("button");

            if (nickname == null || buttonStr == null) {
                ctx.status(400).json(Map.of("error", "nickname과 button 파라미터가 필요합니다."));
                return;
            }

            int button = StringParser.ParseStringToInt(buttonStr);
            UserRequest request = new UserRequest(nickname, button);

            Tier tier = playerInfoService.getUserTier(request);
            List<PlayRecordDto> records = playerInfoService.getRawRecords(request);
            DjClass djClass = playerInfoService.getDjClass(records, button);

            var improvements = recommendationService.recommendImprovements(request);
            var newSongs = recommendationService.recommendNewSongs(request);

            Map<String, Object> response = new HashMap<>();
            response.put("tier", tier);
            response.put("djClass", djClass);
            response.put("improvements", improvements);
            response.put("newSongs", newSongs);

            ctx.json(response);

        } catch (UserNotFoundException | TierNotFoundException e) {
            ctx.status(404).json(Map.of(
                    "error", "NOT_FOUND",
                    "message", e.getMessage()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of(
                    "error", "INTERNAL_ERROR",
                    "message", "서버 내부 오류가 발생했습니다."
            ));
        }


    }
}
