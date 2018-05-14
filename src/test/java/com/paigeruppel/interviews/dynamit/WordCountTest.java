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
    public void shouldConvertAWordCountMapIntoAListOfFormattedStrings() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("the", 25);
        List<String> wordCountList = Arrays.asList("the - 25");
        assertThat(underTest.createWordCountList(wordCountMap), is(wordCountList));
    }

    @Test
    public void shouldConvertAWordCountMapWithMultipleEntriesIntoAListOfFormattedStrings() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("the", 25);
        wordCountMap.put("every", 10);
        List<String> wordCountList = Arrays.asList("the - 25", "every - 10");
        assertThat(underTest.createWordCountList(wordCountMap), is(wordCountList));
    }

    @Test
    public void shouldConvertAListOfWordsIntoAMapOfWordAndOccurences() {
        List<String> words = Arrays.asList("hello", "hello");
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("hello", 2);
        assertThat(underTest.createWordCountMap(words), is(wordCountMap));
    }


}
