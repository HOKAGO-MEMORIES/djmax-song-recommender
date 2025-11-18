package com.hokago_memories.service;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.domain.logic.DjClassCalculator;
import com.hokago_memories.dto.PlayRecordDto;
import com.hokago_memories.dto.UserRequest;
import java.util.List;

public class PlayerInfoService {

    private final OpenApiService openApiService;
    private final DjClassCalculator djClassCalculator;

    public PlayerInfoService(OpenApiService openApiService, DjClassCalculator djClassCalculator) {
        this.openApiService = openApiService;
        this.djClassCalculator = djClassCalculator;
    }

    public List<PlayRecordDto> getRawRecords(UserRequest request) {
        return openApiService.findDjClassBoard(request.nickname(), request.button());
    }

    public Tier getUserTier(UserRequest request) {
        return openApiService.findTier(request.nickname(), request.button());
    }

    public DjClass getDjClass(List<PlayRecordDto> records, int button) {
        return djClassCalculator.calculate(records, button);
    }

    public void validateUserExists(UserRequest request) {
        openApiService.findTier(request.nickname(), request.button());
    }
}
