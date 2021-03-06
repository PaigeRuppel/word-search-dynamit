package com.paigeruppel.interviews.dynamit;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Map.Entry;
import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class WordCount {

    private final Function<Entry<String, Integer>, String> countFormatter = entry -> format("%s - %s", entry.getKey(), entry.getValue());
    private final Comparator<Entry<String, Integer>> byCountDescending = (Entry.<String, Integer>comparingByValue().reversed()).thenComparing(comparingByKey());

    private final Stream<String> reader;

    public WordCount(WordStreamReader reader) {
        this.reader = reader.stream();
    }

    public List<String> createWordCountList(Map<String, Integer> wordCountMap) {
        return wordCountMap.entrySet()
                .stream()
                .sorted(byCountDescending)
                .map(countFormatter)
                .collect(toList());
    }

    public Map<String, Integer> createWordCountMap() {
        return reader.collect(toMap(word -> word.toLowerCase(), word -> 1, Integer::sum));
    }


}
