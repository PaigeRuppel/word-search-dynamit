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

    private static final String PUNCTUATION = "\"|—|,|\\?|(?<![A-Z][a-z])\\.";


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
    public List<String> createRawWordsListFromFile(URI uri) {
        List<String> words = new ArrayList<>();
        try(Stream<String> lines = Files.lines(Paths.get(uri), Charset.defaultCharset())) {
            words = lines
                    .map(line -> line
                            .replaceAll(PUNCTUATION, " ")
                            .split("[\\s]+"))
                    .flatMap(Arrays::stream)
                    .filter(word -> word.length() > 0)
                    .collect(toList());
        }
        catch (IOException e) {

        }
        return words;
    }

    Predicate<String> nonWordFilter = Pattern.compile(PUNCTUATION).asPredicate();
}
