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

import static java.lang.String.format;
import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class WordCount {

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

    public Map<String, Integer> createWordCountMap(String... words) {
        return createWordCountMap(Arrays.asList(words));
    }

    public Map<String, Integer> createWordCountMap(List<String> words) {
        return words.stream()
                .collect(toMap(word -> word.toLowerCase(), word -> 1, Integer::sum));
    }

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
            throw new RuntimeException(e);
        }
        return words;
    }

    public class FileReadingException extends RuntimeException {
        public FileReadingException(URI uri, Exception cause) {
            super(format("Problem reading file from [%s].", uri), cause);
        }
    }

}
