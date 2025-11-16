package repository;

import com.hokago_memories.domain.song.PatternButton;
import com.hokago_memories.domain.song.PatternDifficulty;
import com.hokago_memories.domain.song.Patterns;
import com.hokago_memories.domain.song.Song;
import com.hokago_memories.repository.SongRepository;
import java.util.List;
import java.util.Optional;

public class StubSongRepository implements SongRepository {
    @Override
    public void save(Song song) {
    }

    @Override
    public void saveAll(List<Song> songs) {
    }

    @Override
    public List<Song> findAll() {
        PatternDifficulty newPatternDifficulty1 = new PatternDifficulty(15, 15.2, 200);
        PatternButton newButton1 = new PatternButton(null, null, null, newPatternDifficulty1);
        Patterns newPatterns1 = new Patterns(newButton1, null, null, null);
        Song newSong1 = new Song(722, "PUPA (xi Remix)", "xi", "CP", "CLEAR PASS", newPatterns1);

        PatternDifficulty newPatternDifficulty2 = new PatternDifficulty(14, 14.2, 196);
        PatternButton newButton2 = new PatternButton(null, null, null, newPatternDifficulty2);
        Patterns newPatterns2 = new Patterns(newButton2, null, null, null);
        Song newSong2 = new Song(718, "The Universe", "NieN", "VL3", "V LIBERTY III", newPatterns2);

        PatternDifficulty basicPatternDifficulty1 = new PatternDifficulty(15, 15.3, 202);
        PatternButton basicButton1 = new PatternButton(null, null, null, basicPatternDifficulty1);
        Patterns basicPatterns1 = new Patterns(basicButton1, null, null, null);
        Song basicSong1 = new Song(749, "DIE IN", "TAK, Sobrem", "VE4", "V EXTENSION 4", basicPatterns1);

        PatternDifficulty basicPatternDifficulty2 = new PatternDifficulty(15, 15.2, 200);
        PatternButton basicButton2 = new PatternButton(null, null, null, basicPatternDifficulty2);
        Patterns basicPatterns2 = new Patterns(basicButton2, null, null, null);
        Song basicSong2 = new Song(544, "LIMBO", "Mori+", "EZ2", "COLLABORATION", basicPatterns2);

        return List.of(newSong1, newSong2, basicSong1, basicSong2);
    }

    @Override
    public Optional<Song> findByTitle(int title) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
