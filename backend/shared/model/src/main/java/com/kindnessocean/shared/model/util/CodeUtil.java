package com.kindnessocean.shared.model.util;

import org.springframework.stereotype.Component;

@Component
public class CodeUtil {

    private static final String SEPARATOR = "-";
    private static final String alphabet = "0123456789";
    private static final Integer CODE_LENGTH = 8;
    private static final int alphabetLength = alphabet.length();

    public Integer generateCodeAsNumber() {
        StringBuilder builder = new StringBuilder();

        for (long i = 1; i < CODE_LENGTH; i++) {
            builder.append(getRandomNumber());
        }

        return Integer.valueOf(builder.toString());
    }

    private char getRandomNumber() {
        double rawIndex = Math.random() * alphabetLength;
        int index = (int) rawIndex;
        return alphabet.charAt(index);
    }
}
