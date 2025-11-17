package com.hokago_memories.service;

import com.hokago_memories.domain.song.Song;
import com.hokago_memories.infrastructure.api.NetworkClient;
import com.hokago_memories.infrastructure.parser.JsonParser;
import com.hokago_memories.repository.SongRepository;
import java.util.List;

public class SongDataInitializerService {

    private final NetworkClient networkClient;
    private final SongRepository songRepository;

    public SongDataInitializerService(NetworkClient networkClient, SongRepository songRepository) {
        this.networkClient = networkClient;
        this.songRepository = songRepository;
    }

    public void initializeDatabaseIfNeeded() {
        if (songRepository.count() > 0) {
            System.out.println("DB에 이미 " + songRepository.count() + "개의 곡이 있습니다. 초기화를 건너뜁니다.");
            return;
        }

        try {
            System.out.println("songs.json 파일을 다운로드합니다...");
            String jsonString = networkClient.get("https://v-archive.net/db/songs.json");

            System.out.println("...다운로드 완료. JSON을 파싱합니다...");
            List<Song> allSongs = JsonParser.parseSongs(jsonString);

            System.out.println("..." + allSongs.size() + "개의 곡을 DB에 저장합니다...");
            songRepository.saveAll(allSongs);

            System.out.println("...DB 초기화 완료.");

        } catch (Exception e) {
            System.err.println("DB 초기화 중 심각한 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
}
