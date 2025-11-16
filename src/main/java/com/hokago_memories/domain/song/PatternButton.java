package com.hokago_memories.domain.song;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.util.HashMap;
import java.util.Map;

@Embeddable
public record PatternButton(
        @Embedded @JsonProperty("NM") PatternDifficulty NM,
        @Embedded @JsonProperty("HD") PatternDifficulty HD,
        @Embedded @JsonProperty("MX") PatternDifficulty MX,
        @Embedded @JsonProperty("SC") PatternDifficulty SC
) {

    public Map<String, PatternDifficulty> getAllDifficulties() {
        Map<String, PatternDifficulty> map = new HashMap<>();
        if (NM != null) {
            map.put("NM", NM);
        }
        if (HD != null) {
            map.put("HD", HD);
        }
        if (MX != null) {
            map.put("MX", MX);
        }
        if (SC != null) {
            map.put("SC", SC);
        }
        return map;
    }
}
