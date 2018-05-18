package com.paigeruppel.interviews.dynamit;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;


public class WordCountTest {

    public WordCount underTest;

    @Test
    public void shouldCreateMapGroupedByWord() {
        WordStreamReader reader = new WordStreamReader("yes yes");
        underTest = new WordCount(reader);
        Map<String, Integer> wordCountMap = underTest.createWordCountMap();
        assertThat(wordCountMap, hasEntry("yes", 2));
    }

    @Test
    public void shouldConvertAListOfWordsIntoAMapOfWordAndOccurrences() {
        WordStreamReader reader = new WordStreamReader("hello hello");
        underTest = new WordCount(reader);
        Map<String, Integer> wordCountMap = underTest.createWordCountMap();
        assertThat(wordCountMap, hasEntry("hello", 2));
    }

    @Test
    public void shouldSortWordsAlphabeticallyWhenCountMatches() {
        WordStreamReader reader = new WordStreamReader("aT the boardwalk", "at tHe sea");
        underTest = new WordCount(reader);
        Map<String, Integer> wordCountMap = underTest.createWordCountMap();
        assertThat(underTest.createWordCountList(wordCountMap), contains("at - 2", "the - 2", "boardwalk - 1", "sea - 1"));
    }

}
