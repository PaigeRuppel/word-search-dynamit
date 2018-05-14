package com.paigeruppel.interviews.dynamit;

import com.paigeruppel.interviews.dynamit.WordCount;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
    public void shouldConvertAWordCountMapWithMultipleEntriesIntoAnOrderedListOfFormattedStrings() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("the", 25);
        wordCountMap.put("every", 10);
        wordCountMap.put("of", 15);
        wordCountMap.put("yes", 1);
        wordCountMap.put("at", 25);
        List<String> wordCountList = Arrays.asList("the - 25", "at - 25", "of - 15", "every - 10", "yes - 1");
        assertThat(underTest.createWordCountList(wordCountMap), is(wordCountList));
    }

    @Test
    public void shouldConvertAListOfWordsIntoAMapOfWordAndOccurrences() {
        List<String> words = Arrays.asList("hello", "hello");
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("hello", 2);
        assertThat(underTest.createWordCountMap(words), is(wordCountMap));
    }

    @Test
    public void shouldConvertAListOfWordsIntoAMapOfWordAndOccurrencesIgnoresCase() {
        List<String> words = Arrays.asList(
                "hello",
                "hello",
                "world",
                "wow",
                "Person",
                "person",
                "WoW",
                "HELLO");
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("hello", 3);
        wordCountMap.put("person", 2);
        wordCountMap.put("wow", 2);
        wordCountMap.put("world", 1);
        assertThat(underTest.createWordCountMap(words), is(wordCountMap));
    }

    @Test
    public void shouldReturnAListOfWordsFromAFile() throws URISyntaxException {
        List<String> words = Arrays.asList(
                "WASHINGTON", "Unable", "to", "rest",
                "their", "eyes", "on", "a", "colorful",
                "photograph", "or", "boldface", "Dumbfounded",
                "unsure","of", "what", "to", "do", "next",
                "Without", "an", "illustration", "chart", "or",
                "embedded", "YouTube", "video",
                "I've", "never", "seen", "anything", "like",
                "it", "said", "Mark", "Shelton", "a", "high",
                "school", "teacher", "from", "St.", "Paul", "MN"
        );

        URI uri = getClass().getClassLoader().getResource("ShortParagraph.txt").toURI();
        assertThat(underTest.createRawWordsListFromFile(uri), is(words));
    }

}
