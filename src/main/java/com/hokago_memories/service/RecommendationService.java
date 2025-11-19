package com.hokago_memories.service;

import com.hokago_memories.dto.ImprovementRecommendation;
import com.hokago_memories.dto.NewSongRecommendation;
import com.hokago_memories.dto.PlayRecordDto;
import com.hokago_memories.dto.UserRequest;
import com.hokago_memories.service.recommender.ImprovementRecommender;
import com.hokago_memories.service.recommender.NewSongRecommender;
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
