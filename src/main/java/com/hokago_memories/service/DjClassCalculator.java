package com.hokago_memories.service;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.PlayRecordDto;
import com.hokago_memories.util.Constants;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DjClassCalculator {

    private final double theoreticalMax;

    public DjClassCalculator(double theoreticalMax) {
        this.theoreticalMax = theoreticalMax;
    }

    public DjClass calculate(List<PlayRecordDto> records) {
        Map<Boolean, List<PlayRecordDto>> partitionedRecords = partitionRecords(records);

        List<PlayRecordDto> newPool = sortRecords(partitionedRecords.get(true)).stream().limit(30).toList();
        List<PlayRecordDto> basicPool = sortRecords(partitionedRecords.get(false)).stream().limit(70).toList();

        double newSum = sumDjPower(newPool);
        double basicSum = sumDjPower(basicPool);
        double rawTotal = newSum + basicSum;
        double score = calculateScore(rawTotal);

        return new DjClass(score, rawTotal, basicSum, newSum);
    }

    private Map<Boolean, List<PlayRecordDto>> partitionRecords(List<PlayRecordDto> records) {
        return records.stream()
                .collect(Collectors.partitioningBy(this::isNewCategory));

    }

    private boolean isNewCategory(PlayRecordDto record) {
        boolean isCp = record.dlcCode().equals(Constants.CLEAR_PASS);
        boolean isNewTitle = record.title() >= Constants.NEW_CATEGORY_TITLE;

        return isCp || isNewTitle;
    }

    private List<PlayRecordDto> sortRecords(List<PlayRecordDto> records) {
        return records.stream()
                .sorted(Comparator.comparingDouble(PlayRecordDto::djpower).reversed()).toList();
    }

    private double sumDjPower(List<PlayRecordDto> pool) {
        return pool.stream().mapToDouble(PlayRecordDto::djpower).sum();
    }

    private double calculateScore(double rawTotal) {
        return Math.round((rawTotal * (10000 / theoreticalMax)) * 100) / 100.0;
    }
}
