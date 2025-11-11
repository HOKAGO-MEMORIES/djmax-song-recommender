package repository;

import com.hokago_memories.domain.song.Song;
import com.hokago_memories.util.JsonParser;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JpaSongRepositoryTest {

    private JpaSongRepository repository;
    private List<Song> sampleSongs;

    @BeforeEach
    void setup() {
        // 이곳에서 DB 초기화

        String jsonString = loadJson("sample_songs.json");
        this.sampleSongs = JsonParser.parseSongs(jsonString);
    }

    @Test
    @DisplayName("Song 객체를 저장하고, findById로 조회할 수 있다.")
    void saveAndFindById() {
        Song songToSave = sampleSongs.getFirst();

        repository.save(songToSave);

        Optional<Song> foundSong = repository.findByTitle(songToSave.title());

        Assertions.assertThat(foundSong).isPresent();
        Assertions.assertThat(foundSong.get().name()).isEqualTo(songToSave.name());
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
