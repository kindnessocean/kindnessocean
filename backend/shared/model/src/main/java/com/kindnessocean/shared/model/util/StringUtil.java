package com.kindnessocean.shared.model.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    private static final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int alphabetLength = alphabet.length();

    public static String generateString(long length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be more than zero");
        }

        StringBuilder builder = new StringBuilder();

        for (long i = 0; i < length; i++) {
            double rawIndex = Math.random() * alphabetLength;
            int index = (int) rawIndex;
            builder.append(alphabet.charAt(index));
        }

        return builder.toString();
    }
}
