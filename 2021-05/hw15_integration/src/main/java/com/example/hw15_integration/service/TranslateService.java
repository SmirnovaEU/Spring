package com.example.hw15_integration.service;

import com.example.hw15_integration.domain.DictionaryWord;
import com.example.hw15_integration.domain.Sentence;

import java.util.List;

public interface TranslateService {
   List<String> getSentenceWords(Sentence sentence) throws Exception;

   List<String> wordToLowerCase(List<String> words) throws Exception;

   String wordRouter(String word);

   String writeTranslate(String word);

}
