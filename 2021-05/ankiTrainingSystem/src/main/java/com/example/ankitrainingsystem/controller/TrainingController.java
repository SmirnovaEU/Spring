package com.example.ankitrainingsystem.controller;

import com.example.ankitrainingsystem.model.*;
import com.example.ankitrainingsystem.repository.*;
import com.example.ankitrainingsystem.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TrainingController {
    private final DictionaryRepository dictRepository;
    private final WordRepository wordRepository;
    private final ScheduleRepository scheduleRepository;
    private final SettingsRepository setRepository;
    private final TrainingRepository trainingRepository;
    private final WordService wordService;


    @Autowired
    public TrainingController(DictionaryRepository dictRepository, WordRepository wordRepository, ScheduleRepository scheduleRepository, SettingsRepository setRepository, TrainingRepository trainingRepository, WordService wordService) {
        this.dictRepository = dictRepository;
        this.wordRepository = wordRepository;
        this.scheduleRepository = scheduleRepository;
        this.setRepository = setRepository;
        this.trainingRepository = trainingRepository;
        this.wordService = wordService;
    }

    @PostMapping("/trainings")
    public void createTraining(User user) {
        //TODO: перенести в сервис после исправления получения текущего словаря
        Dictionary dict = dictRepository.findById(1L).orElseThrow(NotFoundException::new);
        int newWordsQuantity = setRepository.findFirstByUSer(user).getNewWordsInTrain();

        List<Word> wordList = wordRepository.findWordsForNewTraining(dict, WordStatus.NEW, newWordsQuantity);
        Training training = new Training(dict, false, wordList);
        trainingRepository.save(training);
        wordService.trainWords(wordList);
    }


}
