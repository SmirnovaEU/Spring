package com.example.hw15_integration.service;

import com.example.hw15_integration.domain.DictionaryWord;
import com.example.hw15_integration.domain.Sentence;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslateServiceIImpl implements TranslateService {

    private static final List<DictionaryWord> DICTIONARY = Arrays.asList(
            new DictionaryWord("asterisk", "звездочка"),
            new DictionaryWord("repeat", "повторить"),
            new DictionaryWord("tickle", "щекотка"),
            new DictionaryWord("blend", "смесь")
    );

    @Override
    public List<String> getSentenceWords(Sentence sentence) throws Exception {
        System.out.println("getting words from: " + sentence.getText());
        return Arrays.asList(sentence.getText().split(" "));
    }

    @Override
    public List<String> wordToLowerCase(List<String> words) throws Exception {

        return words.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public boolean isWordInDictionary(String word) {
        return DICTIONARY.stream().anyMatch(dictWord -> dictWord.getName().equals(word));
    }


    public DictionaryWord getWordFromDictionary(String word) {
        return DICTIONARY.stream()
                .filter(dictWord -> dictWord.getName().equals(word))
                .findFirst()
                .orElseThrow();
    }

    @Override
    @Router(inputChannel = "filteredChannel")
    public String wordRouter(String word) {
        if (isWordInDictionary(word)) {
            System.out.println("word for translate: " + word);
            return "translatedChannel";
        }else {
            System.out.println("new word: " + word);
            return "newWordChannel";
        }
    }

    @Override
    public String writeTranslate(String word) {
        DictionaryWord dictWord = getWordFromDictionary(word);
        System.out.println("Translate for word " + word + " = " + dictWord.getTranslate());
        return dictWord.getTranslate();
    }

}
