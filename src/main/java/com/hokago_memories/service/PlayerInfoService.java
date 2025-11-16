package com.hokago_memories.service;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.PlayRecordDto;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.domain.UserRequest;
import java.util.List;

public class PlayerInfoService {

    private final OpenApiService openApiService;
    private final DjClassCalculator djClassCalculator;

    public PlayerInfoService(OpenApiService openApiService, DjClassCalculator djClassCalculator) {
        this.openApiService = openApiService;
        this.djClassCalculator = djClassCalculator;
    }

    public Tier getUserTier(UserRequest request) {
        return openApiService.findTier(request.nickname(), request.button());
    }

    public DjClass getDjClass(UserRequest request) {
        List<PlayRecordDto> records = openApiService.findDjClassBoard(request.nickname(), request.button());
        return djClassCalculator.calculate(records, request.button());
    }
}
