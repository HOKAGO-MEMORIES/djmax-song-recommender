package com.hokago_memories.service;

import com.hokago_memories.repository.SongRepository;

public class TheoreticalMaxCalculator {

    private final SongRepository songRepository;
    private final DjPowerRules djPowerRules;

    public TheoreticalMaxCalculator(SongRepository songRepository, DjPowerRules djPowerRules) {
        this.songRepository = songRepository;
        this.djPowerRules = djPowerRules;
    }

    public double calculate() {

    }
}
