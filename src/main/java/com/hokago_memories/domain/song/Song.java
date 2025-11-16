package com.hokago_memories.domain.song;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Song {

    @Id
    int title;
    String name;
    String composer;
    String dlcCode;
    String dlc;

    @Embedded
    Patterns patterns;

    protected Song() {
    }

    @JsonCreator
    public Song(
            @JsonProperty("title") int title,
            @JsonProperty("name") String name,
            @JsonProperty("composer") String composer,
            @JsonProperty("dlcCode") String dlcCode,
            @JsonProperty("dlc") String dlc,
            @JsonProperty("patterns") Patterns patterns) {
        this.title = title;
        this.name = name;
        this.composer = composer;
        this.dlcCode = dlcCode;
        this.dlc = dlc;
        this.patterns = patterns;
    }

    public int title() {
        return title;
    }

    public String name() {
        return name;
    }

    public String composer() {
        return composer;
    }

    public String dlcCode() {
        return dlcCode;
    }

    public String dlc() {
        return dlc;
    }

    public Patterns patterns() {
        return patterns;
    }
}
