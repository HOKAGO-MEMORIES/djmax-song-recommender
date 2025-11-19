package com.hokago_memories.service.api;

import com.hokago_memories.domain.Tier;
import com.hokago_memories.dto.internal.PlayRecordDto;
import java.util.List;

public interface OpenApiService {

    Tier findTier(String nickname, int button);

    List<PlayRecordDto> findDjClassBoard(String nickname, int button);
}
