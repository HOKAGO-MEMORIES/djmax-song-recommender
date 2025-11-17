package com.hokago_memories.domain.logic;

import com.hokago_memories.dto.TopRecords;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class SongPartitioner {

    private SongPartitioner() {
    }

    public static <T> TopRecords<T> selectTopRecords(
            List<T> items,                      // 곡 리스트
            Predicate<T> isNewPredicate,        // 분류 기준
            ToDoubleFunction<T> powerExtractor  // 정렬 기준
    ) {
        Map<Boolean, List<T>> partitioned = partitionItems(items, isNewPredicate);
        List<T> topNew = extractTopRanked(partitioned, true, 30, powerExtractor);
        List<T> topBasic = extractTopRanked(partitioned, false, 70, powerExtractor);

        return new TopRecords<>(topNew, topBasic);
    }

    private static <T> Map<Boolean, List<T>> partitionItems(
            List<T> items,
            Predicate<T> classifier
    ) {
        return items.stream()
                .collect(Collectors.partitioningBy(classifier));

    }

    private static <T> List<T> extractTopRanked(
            Map<Boolean, List<T>> partitionedPool,
            boolean poolKey,
            int limit,
            ToDoubleFunction<T> sortCriteria
    ) {
        return partitionedPool.get(poolKey).stream()
                .sorted(Comparator.comparingDouble(sortCriteria).reversed())
                .limit(limit)
                .toList();
    }
}
