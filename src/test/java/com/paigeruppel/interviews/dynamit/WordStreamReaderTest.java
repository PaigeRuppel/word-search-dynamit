package com.paigeruppel.interviews.dynamit;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WordStreamReaderTest {

    @Test
    public void shouldReadCleanWords() throws URISyntaxException, IOException {
        WordStreamReader underTest = new WordStreamReader("CleanWords.txt");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("clean", "words"));
    }

    @Test
    public void shouldIgnoreTrailingPunctuation() throws URISyntaxException, IOException {
        WordStreamReader underTest = new WordStreamReader("TrailingPunctuation.txt");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("comma", "word", "quotation"));
    }

    @Test
    public void shouldAllowSomePunctuationInsideWords() throws URISyntaxException, IOException {
        WordStreamReader underTest = new WordStreamReader("ContractionsHyphens.txt");
        Stream<String> result = underTest.stream();
        assertThat(result.collect(toList()), contains("contraction'll", "leave-hyphen"));
    }
}
