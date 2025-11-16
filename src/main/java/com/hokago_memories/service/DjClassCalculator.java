package com.hokago_memories.service;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.PlayRecordDto;
import com.hokago_memories.domain.TopRecords;
import com.hokago_memories.util.SongClassifier;
import com.hokago_memories.util.SongPartitioner;
import java.util.List;

public class DjClassCalculator {

    private final double theoreticalMax;

    public DjClassCalculator(double theoreticalMax) {
        this.theoreticalMax = theoreticalMax;
    }

    public DjClass calculate(List<PlayRecordDto> records) {
        TopRecords<PlayRecordDto> topRecords = SongPartitioner.selectTopRecords(
                records,
                SongClassifier::isNewCategory,
                PlayRecordDto::djpower
        );

        double newSum = sumDjPower(topRecords.topNew());
        double basicSum = sumDjPower(topRecords.topBasic());
        double rawTotal = newSum + basicSum;
        double score = calculateScore(rawTotal);

        return new DjClass(score, rawTotal, basicSum, newSum);
    }


    private double sumDjPower(List<PlayRecordDto> pool) {
        return pool.stream().mapToDouble(PlayRecordDto::djpower).sum();
    }

    private double calculateScore(double rawTotal) {
        return Math.round((rawTotal * (10000 / theoreticalMax)) * 100) / 100.0;
    }
}
