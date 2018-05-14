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
import static java.util.stream.Collectors.toMap;

public class WordCount {
    public List<String> createWordCountList(Map<String, Integer> wordCountMap) {
        return wordCountMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.toList());
    }

    public Map<String, Integer> createWordCountMap(List<String> words) {
        Map<String, Integer> wordCountMap = words.stream()
                                            .collect(toMap(word -> word.toLowerCase(), word -> 1, Integer::sum));
        return wordCountMap;
    }
}
