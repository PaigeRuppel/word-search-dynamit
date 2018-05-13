package com.paigeruppel.interviews.dynamit;

import com.paigeruppel.interviews.dynamit.WordCount;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<String> wordCountList = new ArrayList<>();
        wordCountList.add("the - 25");
        assertThat(underTest.createWordCountList(wordCountMap), is(wordCountList));
    }
}
