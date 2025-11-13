package util;

import com.hokago_memories.domain.song.Song;
import com.hokago_memories.util.parser.JsonParser;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonParserTest {

    private List<Song> songs;

    @BeforeEach
    void setup() {
        String jsonString = TestDataLoader.loadJson("sample_songs.json");
        this.songs = JsonParser.parseSongs(jsonString);
    }

    @Test
    @DisplayName("곡 목록 JSON 파일을 불러와 List<Song> 객체를 생성한다.")
    void parse_jsonToSong() {
        Assertions.assertThat(songs).isNotEmpty();
        Assertions.assertThat(songs.getFirst().name()).isEqualTo("비상 ~Stay With Me~");
    }

    @Test
    @DisplayName("곡 목록 JSON 파일을 불러와 List<Song> 객체를 생성하고 파일 마지막 곡을 확인한다.")
    void parse_jsonToSong_check_lastSong() {
        Assertions.assertThat(songs).isNotEmpty();
        Assertions.assertThat(songs.getLast().name()).isEqualTo("AURORA");
        Assertions.assertThat(songs.getLast().composer()).isEqualTo("DIEIN");
        Assertions.assertThat(songs.getLast().patterns().b4().HD().level()).isEqualTo(6);
        Assertions.assertThat(songs.getLast().patterns().b6().SC().floor()).isEqualTo(4.1);
        Assertions.assertThat(songs.getLast().patterns().b8().NM().floor()).isNull();
    }
}
