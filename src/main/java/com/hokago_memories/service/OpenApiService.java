package com.hokago_memories.service;

import com.hokago_memories.domain.Tier;
import com.hokago_memories.dto.PlayRecordDto;
import java.util.List;

public interface OpenApiService {

    Tier findTier(String nickname, int button);

    List<PlayRecordDto> findDjClassBoard(String nickname, int button);
}
