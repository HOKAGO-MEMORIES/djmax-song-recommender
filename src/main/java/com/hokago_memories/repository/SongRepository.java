package com.hokago_memories.repository;

import com.hokago_memories.domain.song.Song;
import java.util.List;
import java.util.Optional;

public interface SongRepository {

    void save(Song song);

    void saveAll(List<Song> songs);

    List<Song> findAll();

    Optional<Song> findByTitle(int title);

    long count();

    void clear();
}
