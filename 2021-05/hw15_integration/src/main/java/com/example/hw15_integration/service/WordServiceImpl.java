package com.example.hw15_integration.service;

import com.example.hw15_integration.domain.DictionaryWord;
import com.example.hw15_integration.domain.Sentence;
import com.example.hw15_integration.integration.Translator;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {
    private final Translator translator;
    private final List<Sentence> SENTENCE_LIST = Arrays.asList(new Sentence("Repeat instructions following the single asterisk as directed"),
            new Sentence("Anti tickle Merino Blend "));
    private static final int ORDERS_DELAY_MILLS = 7000;

    public WordServiceImpl(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void startWordsLoop() throws Exception {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        for (Sentence sentence : SENTENCE_LIST) {
            Thread.sleep(ORDERS_DELAY_MILLS);

            pool.execute(() -> {
                System.out.println("Sentence: " + sentence.getText());
                Collection<String> words = translator.process(sentence);
                System.out.println("Ready words: " + String.join(",", words));
            });

    }

    }
}
