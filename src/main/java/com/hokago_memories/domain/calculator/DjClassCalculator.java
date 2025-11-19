package com.hokago_memories.domain.calculator;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.rule.SongClassifier;
import com.hokago_memories.domain.rule.SongPartitioner;
import com.hokago_memories.dto.internal.PlayRecordDto;
import com.hokago_memories.dto.internal.TheoreticalMax;
import com.hokago_memories.dto.internal.TopRecords;
import java.util.List;

public class DjClassCalculator {

    private final TheoreticalMax theoreticalMax;

    public DjClassCalculator(TheoreticalMax theoreticalMax) {
        this.theoreticalMax = theoreticalMax;
    }

    public DjClass calculate(List<PlayRecordDto> records, int button) {
        TopRecords<PlayRecordDto> topRecords = SongPartitioner.selectTopRecords(
                records,
                SongClassifier::isNewCategory,
                PlayRecordDto::djpower
        );

        double newSum = sumDjPower(topRecords.topNew());
        double basicSum = sumDjPower(topRecords.topBasic());
        double rawTotal = newSum + basicSum;
        double score = calculateScore(rawTotal, button);

        return new DjClass(score, rawTotal, basicSum, newSum);
    }


    private double sumDjPower(List<PlayRecordDto> pool) {
        return pool.stream().mapToDouble(PlayRecordDto::djpower).sum();
    }

    private double calculateScore(double rawTotal, int button) {
        return Math.round((rawTotal * (10000 / theoreticalMax.getForButton(button))) * 100) / 100.0;
    }
}
