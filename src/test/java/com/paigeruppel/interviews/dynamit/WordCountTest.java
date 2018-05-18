package com.paigeruppel.interviews.dynamit;

import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;


public class WordCountTest {

    public WordCount underTest;

    @Test
    public void shouldCreateMapGroupedByWord() {
        WordStreamReader reader = new WordStreamReader("yes yes");
        underTest = new WordCount(reader);
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("yes", 2);
        assertThat(underTest.createWordCountMap(), is(expectedMap));
    }

    @Test
    public void shouldSortWordsAlphabeticallyWhenCountMatches() {
//        Map<String, Integer> wordCountMap = new HashMap<>();
//        wordCountMap.put("the", 20);
//        wordCountMap.put("at", 20);
//        wordCountMap.put("yes", 20);
//        assertThat(underTest.createWordCountList(wordCountMap), contains("at - 20", "the - 20", "yes - 20"));
    }

    @Test
    public void shouldConvertAListOfWordsIntoAMapOfWordAndOccurrences() {
//        Map<String, Integer> wordCountMap = underTest.createWordCountMap("hello", "hello");
//        assertThat(wordCountMap, hasEntry("hello", 2));
    }

    @Test
    public void shouldConvertToMapAndIgnoreCase() {
//        Map<String, Integer> wordCounts = underTest.createWordCountMap("Wow", "WOW", "WoW");
//        assertThat(wordCounts, hasEntry("wow", 3));
    }

    @Test
    public void shouldReturnAListOfWordsFromAFile() throws URISyntaxException {
//        List<String> words = Arrays.asList(
//                "WASHINGTON", "Unable", "to", "rest", "their", "eyes", "on",
//                "a", "colorful", "photograph", "or", "boldface",
//                "Dumbfounded", "unsure", "of", "what", "to", "do", "next",
//                "Without", "an", "illustration", "chart", "or", "embedded",
//                "YouTube", "video", "I've", "never", "seen", "anything",
//                "like", "it", "said", "Mark", "Shelton", "a", "high",
//                "school", "teacher", "from", "St.", "Paul", "MN",
//                "There", "are", "no", "bullet", "points", "no", "highlighted",
//                "parts", "I've", "looked", "everywhere", "there's", "nothing", "here", "but", "words",
//                "At", "a", "deafening", "sigh", "was", "heard", "across", "the",
//                "country", "as", "the", "nation", "grappled", "with", "the", "daunting",
//                "cascade", "of", "syllables", "whose", "unfamiliar", "letter-upon-letter", "structure",
//                "stretched", "on", "for", "an", "endless", "words"
//        );
//
//        String regex = "\"|—|,|\\?|[\\s]+|(?<![A-Z][a-z])\\.|(a\\.m\\.|p\\.m\\.)|:|\\d";
//        URI uri = testFileUri("ShortParagraph.txt");
//        Pattern pattern = Pattern.compile("\\w");
//        assertThat(underTest.createRawWordsListFromFile(uri, regex, pattern.asPredicate()).subList(1, words.size()), is(words.subList(1, words.size())));
    }

    public URI testFileUri(String name) throws URISyntaxException {
        return getClass().getClassLoader().getResource(name).toURI();
    }

    @Test(expected=WordCount.FileReadingException.class)
    public void shouldThrowFileReadingExceptionOnReadingFailure() throws URISyntaxException{
        underTest.createRawWordsListFromFile(new URI("file:///does/not/exist"), null, null);
    }
}
