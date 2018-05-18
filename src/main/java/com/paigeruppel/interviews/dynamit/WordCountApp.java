package com.paigeruppel.interviews.dynamit;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class WordCountApp {

    private static final String REGEX = "\"|—|,|\\?|[\\s]+|(?<![A-Z][a-z])\\.|(a\\.m\\.|p\\.m\\.)|:|\\d";

    public static void main(String[] args) {
//        WordCount count = new WordCount();
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter the name of the file: ");
//        File file = new File(in.nextLine()).getAbsoluteFile();
//
//        URI uri = file.toURI();
//        Predicate<String> patternToMatch = Pattern
//                                            .compile("\\w")
//                                            .asPredicate();
//        List<String> rawWords = count.createRawWordsListFromFile(uri, REGEX, patternToMatch);
//        Map<String, Integer> wordCountMap = count.createWordCountMap(rawWords);
//        count.createWordCountList(wordCountMap).stream().forEach(System.out::println);
//
//        in.close();
    }
}
