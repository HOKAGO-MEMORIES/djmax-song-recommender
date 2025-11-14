package com.hokago_memories.service;

import com.hokago_memories.domain.Tier;
import com.hokago_memories.domain.UserRequest;

public class PlayerInfoService {

    private final OpenApiService openApiService;

    public PlayerInfoService(OpenApiService openApiService) {
        this.openApiService = openApiService;
    }

    public Tier getUserTier(UserRequest request) {
        return openApiService.findTier(request.nickname(), request.button());
    }
}
