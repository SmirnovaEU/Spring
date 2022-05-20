package com.example.hw15_integration.integration;

import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class WordFilter {
    private static final int MIN = 2;

    @Filter
    public static boolean isLongerMin(String word) {
        System.out.println("Filtering " + word);
        return word.length() > MIN;
    }
}
