package com.paigeruppel.interviews.dynamit;

import com.paigeruppel.interviews.dynamit.WordCount;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class WordCountTest {

    private WordCount underTest;

    @Before
    public void setup() {
        underTest = new WordCount();
    }

    @Test
    public void shouldConvertAWordCountMapWithMultipleEntriesIntoAListOfFormattedStrings() {
        Map<String, Long> wordCountMap = new HashMap<>();
        wordCountMap.put("the", Long.valueOf(25));
        wordCountMap.put("every", Long.valueOf(10));
        List<String> wordCountList = Arrays.asList("the - 25", "every - 10");
        assertThat(underTest.createWordCountList(wordCountMap), is(wordCountList));
    }

    @Test
    public void shouldConvertAListOfWordsIntoAMapOfWordAndOccurrences() {
        List<String> words = Arrays.asList("hello", "hello");
        Map<String, Long> wordCountMap = new HashMap<>();
        wordCountMap.put("hello", Long.valueOf(2));
        assertThat(underTest.createWordCountMap(words), is(wordCountMap));
    }

    @Test
    public void shouldConvertAnExtendedListOfWordsIntoAMapOfWordAndOccurrences() {
        List<String> words = Arrays.asList(
                "hello",
                "there",
                "person",
                "wow",
                "person",
                "hello",
                "hello",
                "world"
        );
        Map<String, Long> wordCountMap = new HashMap<>();
        wordCountMap.put("hello", Long.valueOf(3));
        wordCountMap.put("there", Long.valueOf(1));
        wordCountMap.put("person", Long.valueOf(2));
        wordCountMap.put("wow", Long.valueOf(1));
        wordCountMap.put("world", Long.valueOf(1));

        assertThat(underTest.createWordCountMap(words), is(wordCountMap));
    }


}
