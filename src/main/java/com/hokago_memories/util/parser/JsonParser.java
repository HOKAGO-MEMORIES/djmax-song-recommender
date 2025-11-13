package com.hokago_memories.util.parser;

import com.hokago_memories.domain.song.Song;
import java.util.List;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class JsonParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonParser() {
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
}
