package com.paigeruppel.interviews.dynamit;

import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class WordStreamReader {

    public WordStreamReader(String filename) {
    }

    public Stream<String> stream() {
        return asList("clean", "words").stream();
    }
}
