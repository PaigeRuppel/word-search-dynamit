package com.paigeruppel.interviews.dynamit;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class WordStreamReaderTest {

    @Test
    public void shouldReadFile() throws URISyntaxException, IOException {
        Path singleWordFilePath = Paths.get(getClass().getClassLoader().getResource("SingleWord.txt").toURI());
        WordStreamReader underTest = new WordStreamReader(singleWordFilePath);
    }
    @Test
    public void shouldReadCleanWords() throws URISyntaxException, IOException {
        WordStreamReader underTest = new WordStreamReader("clean words");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("clean", "words"));
    }

    @Test
    public void shouldIgnoreTrailingPunctuation() throws URISyntaxException, IOException {
        WordStreamReader underTest = new WordStreamReader("comma, word quotation\"");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("comma", "word", "quotation"));
    }

    @Test
    public void shouldAllowSomePunctuationInsideWords() throws URISyntaxException, IOException {
        WordStreamReader underTest = new WordStreamReader("contraction'll leave-hyphen");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("contraction'll", "leave-hyphen"));
    }

    @Test
    public void shouldReadMultipleLinesAsSingleStream() throws URISyntaxException, IOException {
        WordStreamReader underTest = new WordStreamReader("first line", "second line", "third line");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("first", "line", "second", "line", "third", "line"));
    }
}
