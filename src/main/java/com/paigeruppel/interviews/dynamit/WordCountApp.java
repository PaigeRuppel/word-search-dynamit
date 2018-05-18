package com.paigeruppel.interviews.dynamit;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.lang.ClassLoader.getSystemResource;

public class WordCountApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the name of the file: ");
        WordCountApp app = new WordCountApp(in.nextLine());
        app.printResults();

        in.close();
    }

    private WordCount count;

    public WordCountApp(String filename) {
        try {
            Path filepath = getPath(filename);
            WordStreamReader reader = new WordStreamReader(filepath);
            this.count = new WordCount(reader);
        } catch (IOException ex) {
            throw new FileReadingException(ex);
        }
    }

    private void printResults() {
        Map<String, Integer> wordCountMap = count.createWordCountMap();
        List<String> results = count.createWordCountList(wordCountMap);
        results.stream().forEach(System.out::println);
    }

    // throwing null pointer on exception - fix this
    private Path getPath(String filename) {
        Path filepath;
        try {
            filepath = Paths.get(getSystemResource(filename).toURI());
        } catch (URISyntaxException e) {
            throw new FileReadingException(e);
        }
        return filepath;
    }
}

