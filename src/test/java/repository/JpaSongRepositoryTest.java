package repository;

import com.hokago_memories.domain.song.Song;
import com.hokago_memories.repository.impl.JpaSongRepository;
import com.hokago_memories.util.JsonParser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.TestDataLoader;

public class JpaSongRepositoryTest {

    private EntityManager em;
    private JpaSongRepository repository;
    private List<Song> sampleSongs;

    @BeforeEach
    void setup() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-db-unit");
        em = emf.createEntityManager();
        repository = new JpaSongRepository(em);
        repository.clear();

        String jsonString = TestDataLoader.loadJson("sample_songs.json");
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

}
