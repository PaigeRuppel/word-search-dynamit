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

public class WordStreamReader {

    private final Path wordsFile;

    public WordStreamReader(String filename) throws URISyntaxException {
        wordsFile = Paths.get(getClass().getClassLoader().getResource(filename).toURI());
    }

    public Stream<String> stream() throws IOException {
        Function<String, Collection<String>> wordExtractor = new Function<String, Collection<String>>() {
            @Override
            public Collection<String> apply(String line) {
                Matcher wordBoundaryMatcher = Pattern.compile("([\\w-']+)").matcher(line);

                Collection<String> words = new ArrayList<>();
                while(wordBoundaryMatcher.find()) {
                    words.add(wordBoundaryMatcher.group());
                }
                return words;
            }
        };
        return wordExtractor.apply(Files.lines(wordsFile).findFirst().get()).stream();
    }
}
