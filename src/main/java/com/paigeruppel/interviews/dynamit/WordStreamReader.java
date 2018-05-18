package com.paigeruppel.interviews.dynamit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Stream.concat;

public class WordStreamReader {

    private static final Pattern WORD_PATTERN = Pattern.compile("(\\b[a-zA-Z'-]+)");
    private final Stream<String> lines;

    private final Function<String, Collection<String>> wordExtractor = new Function<String, Collection<String>>() {
        @Override
        public Collection<String> apply(String line) {
            Matcher wordBoundaryMatcher = WORD_PATTERN.matcher(line);
            Collection<String> words = new ArrayList<>();
            while (wordBoundaryMatcher.find()) {
                words.add(wordBoundaryMatcher.group());
            }
            return words;
        }
    };

    private Stream<String> wordStream = Stream.empty();

    public WordStreamReader(Path filepath) throws IOException {
        this.lines = Files.lines(filepath);
    }

    /*Constructor to support testing*/
    public WordStreamReader(String... lines) {
        this.lines = asList(lines).stream();
    }

    /*Constructor to support testing*/
    public WordStreamReader(FileStreamer streamer) {
        try {
            this.lines = streamer.stream();
        } catch (IOException e) {
            throw new FileReadingException(e);
        }
    }

    public Stream<String> stream() {
        this.lines.map(wordExtractor).forEach(line -> wordStream = concat(wordStream, line.stream()));
        return wordStream;
    }

    public interface FileStreamer {
        Stream<String> stream() throws IOException;
    }
}
