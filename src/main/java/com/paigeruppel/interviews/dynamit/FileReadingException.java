package com.paigeruppel.interviews.dynamit;

import java.net.URI;
import java.nio.file.Path;

import static java.lang.String.format;

public class FileReadingException extends RuntimeException {
    public FileReadingException(Exception cause) {
        super(format("Problem reading file", cause));
    }
}
