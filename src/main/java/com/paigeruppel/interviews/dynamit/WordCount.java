package com.paigeruppel.interviews.dynamit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordCount {
    public List<String> createWordCountList(Map<String, Long> wordCountMap) {
        return wordCountMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.toList());
    }

    public Map<String, Long> createWordCountMap(List<String> words) {
        Map<String, Long> wordCountMap = words.stream().collect(
                                                Collectors.groupingBy(
                                                Function.identity(), Collectors.counting()
                                                )
                                            );

        return wordCountMap;
    }
}
