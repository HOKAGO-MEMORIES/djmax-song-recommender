package util;

import com.hokago_memories.domain.song.Song;
import com.hokago_memories.util.JsonParser;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonParserTest {

    @Test
    @DisplayName("곡 목록 JSON 파일을 불러와 songs 객체를 생성한다.")
    void parse_jsonToSong() {
        String jsonString = loadJson("sample_songs.json");

        List<Song> songs = JsonParser.parseSongs(jsonString);

        Assertions.assertThat(songs).isNotEmpty();
        Assertions.assertThat(songs.getFirst().name()).isEqualTo("비상 ~Stay With Me~");
    }

    private String loadJson(String fileName) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
