package com.hokago_memories.infrastructure.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hokago_memories.domain.song.Song;
import java.util.List;

public class JsonParser {

    private static final ObjectMapper objectMapper = createObjectMapper();

    private JsonParser() {
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);

        return mapper;
    }

    public static List<Song> parseSongs(String allSongs) {
        try {
            return objectMapper.readValue(allSongs,
                    new TypeReference<List<Song>>() {
                    });

        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }

    public static <T> T parse(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패 (" + clazz.getSimpleName() + "): " + e.getMessage(), e);
        }
    }
}
