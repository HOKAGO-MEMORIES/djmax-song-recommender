package com.hokago_memories.service.recommendation;

import com.hokago_memories.dto.internal.PlayRecordDto;
import com.hokago_memories.dto.request.UserRequest;
import com.hokago_memories.dto.response.ImprovementRecommendation;
import com.hokago_memories.dto.response.NewSongRecommendation;
import com.hokago_memories.service.PlayerInfoService;
import java.util.List;

public class RecommendationService {

    private final ImprovementRecommender improvementRecommender;
    private final NewSongRecommender newSongRecommender;
    private final PlayerInfoService playerInfoService;


    public RecommendationService(ImprovementRecommender improvementRecommender,
                                 NewSongRecommender newSongRecommender,
                                 PlayerInfoService playerInfoService) {
        this.improvementRecommender = improvementRecommender;
        this.newSongRecommender = newSongRecommender;
        this.playerInfoService = playerInfoService;
    }

    public List<ImprovementRecommendation> recommendImprovements(UserRequest request) {
        List<PlayRecordDto> records = playerInfoService.getRawRecords(request);

        return improvementRecommender.recommend(records, request.button());
    }

    public List<NewSongRecommendation> recommendNewSongs(UserRequest request) {
        List<PlayRecordDto> records = playerInfoService.getRawRecords(request);

        return newSongRecommender.recommend(records, request.button());
    }
}
