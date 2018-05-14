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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class WordCount {
    public List<String> createWordCountList(Map<String, Integer> wordCountMap) {
        return wordCountMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(toList());
    }

    public Map<String, Integer> createWordCountMap(List<String> words) {
        Map<String, Integer> wordCountMap = words.stream()
                                            .collect(toMap(word -> word.toLowerCase(), word -> 1, Integer::sum));
        return wordCountMap;
    }

    public List<String> createRawWordsListFromFile(URI uri) {
        List<String> words = new ArrayList<>();
        try(Stream<String> lines = Files.lines(Paths.get(uri), Charset.defaultCharset())) {
            words = lines
                    .map(line -> line.split("[\\s]+"))
                    .flatMap(Arrays::stream)
                    .collect(toList());
        }
        catch (IOException e) {
        }
        return words;
    }
}
