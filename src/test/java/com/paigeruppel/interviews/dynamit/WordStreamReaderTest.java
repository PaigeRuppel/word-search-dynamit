package com.paigeruppel.interviews.dynamit;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class WordStreamReaderTest {

    @Test
    public void shouldReadCleanWords() {
        WordStreamReader underTest = new WordStreamReader("CleanWords.txt");

        Stream<String> result = underTest.stream();

        assertThat(result.collect(toList()), contains("clean", "words"));
    }
}
