package com.paigeruppel.interviews.dynamit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCount {
    public List<String> createWordCountList(Map<String, Integer> wordCountMap) {
        return wordCountMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.toList());
    }
}
