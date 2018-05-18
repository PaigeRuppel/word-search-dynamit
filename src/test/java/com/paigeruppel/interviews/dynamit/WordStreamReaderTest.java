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
    public void shouldReadCleanWords() {
        WordStreamReader underTest = new WordStreamReader("clean words");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("clean", "words"));
    }

    @Test
    public void shouldIgnoreTrailingPunctuation() {
        WordStreamReader underTest = new WordStreamReader("comma, word quotation\"");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("comma", "word", "quotation"));
    }

    @Test
    public void shouldAllowSomePunctuationInsideWords() {
        WordStreamReader underTest = new WordStreamReader("contraction'll leave-hyphen");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("contraction'll", "leave-hyphen"));
    }

    @Test
    public void shouldReadMultipleLinesAsSingleStream() {
        WordStreamReader underTest = new WordStreamReader("first line", "second line", "third line");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("first", "line", "second", "line", "third", "line"));
    }

    @Test
    public void shouldIgnoreDigits() {
        WordStreamReader underTest = new WordStreamReader("8675309 jenny");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("jenny"));
    }


    @Test(expected=FileReadingException.class)
    public void shouldThrowFileReadingExceptionOnReadingFailure() throws IOException {
            WordStreamReader.FileStreamer badStreamer = new WordStreamReader.FileStreamer() {
            @Override
            public Stream<String> stream() throws IOException {
                throw new IOException();
            }
        };
        new WordStreamReader(badStreamer);
    }
}
