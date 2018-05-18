package com.paigeruppel.interviews.dynamit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

public class WordStreamReader {

    private static final Pattern WORD_PATTERN = Pattern.compile("([\\w-']+)");

    private final Path wordsFile;

    private Stream<String> wordStream = Stream.empty();

    private final Function<String, Collection<String>> wordExtractor = line -> {
        Matcher wordBoundaryMatcher = WORD_PATTERN.matcher(line);
        Collection<String> words = new ArrayList<>();
        while(wordBoundaryMatcher.find()) {
            words.add(wordBoundaryMatcher.group());
        }
        return words;
    };

    public WordStreamReader(String filename) throws URISyntaxException {
        wordsFile = Paths.get(getClass().getClassLoader().getResource(filename).toURI());
    }

    public Stream<String> stream() throws IOException {
        Files.lines(wordsFile).map(wordExtractor).forEach(lineWords -> wordStream = concat(wordStream, lineWords.stream()));
        return wordStream;
    }
}
