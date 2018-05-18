package com.paigeruppel.interviews.dynamit;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    private WordStreamReader reader;

    public WordCountApp(String filename) {
            buildStreamReader(filename);
            this.count = new WordCount(reader);
    }

    private void buildStreamReader(String filename) {
        try {
            Path filePath = Paths.get(getSystemResource(filename).toURI());
            reader = new WordStreamReader(filePath);
        } catch (Exception ex) {
            throw new FileReadingException(ex);
        }
    }

    private void printResults() {
        Map<String, Integer> wordCountMap = count.createWordCountMap();
        List<String> results = count.createWordCountList(wordCountMap);
        results.stream().forEach(System.out::println);
    }
}

