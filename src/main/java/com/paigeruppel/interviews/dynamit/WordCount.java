package com.paigeruppel.interviews.dynamit;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class WordCount {

    private static final String PUNCTUATION = "\"|—|,|\\?|";
    private static final String SENTENCE_ENDING_PERIOD = "(?<![A-Z][a-z])\\.";
    private static final String TIMESTAMP = "(1[012]|[1-9]):[0-5][0-9](\\\\s)?(?i)(a.m.|p.m.)";

    public List<String> createWordCountList(Map<String, Integer> wordCountMap) {
        return wordCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>
                    comparingByValue()
                        .reversed()
                    .thenComparing(comparingByKey()))
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(toList());
    }

    public Map<String, Integer> createWordCountMap(List<String> words) {
        Map<String, Integer> wordCountMap = words.stream()
                                            .collect(toMap(word -> word.toLowerCase(), word -> 1, Integer::sum));
        return wordCountMap;
    }

    // would be nice to be able to add behavior parameterization here? give option of deciding
    // what to filter out at runtime...
    public List<String> createRawWordsListFromFile(URI uri, String regex, Predicate<String> patternToMatch) {
        List<String> words = new ArrayList<>();
        try(Stream<String> lines = Files.lines(Paths.get(uri), Charset.defaultCharset())) {
            words = lines
                    .map(line -> line
                            .split(regex))
                    .flatMap(Arrays::stream)
                    .filter(patternToMatch)
                    .collect(toList());
        }
        catch (IOException e) {

        }
        return words;
    }

}
