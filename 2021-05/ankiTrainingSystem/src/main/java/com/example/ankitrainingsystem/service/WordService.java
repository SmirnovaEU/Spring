package com.example.ankitrainingsystem.service;

import com.example.ankitrainingsystem.controller.NotFoundException;
import com.example.ankitrainingsystem.dto.NewWordDto;
import com.example.ankitrainingsystem.mapper.WordMapper;
import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Schedule;
import com.example.ankitrainingsystem.model.Word;
import com.example.ankitrainingsystem.repository.DictionaryRepository;
import com.example.ankitrainingsystem.repository.ScheduleRepository;
import com.example.ankitrainingsystem.repository.WordRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {
    private final WordRepository repository;
    private final DictionaryRepository dictRepository;
    private final ScheduleRepository scheduleRepository;
    private static final WordMapper wordMapper = Mappers.getMapper(WordMapper.class);

    public WordService(WordRepository repository, DictionaryRepository dictRepository, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.dictRepository = dictRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public void createWord(NewWordDto wordDto, long dictId) {
        Word word = wordMapper.dtoToWord(wordDto);

        Dictionary dictionary = dictRepository.findById(dictId).orElseThrow(NotFoundException::new);
        word.setDictionary(dictionary);
        repository.save(word);
        Schedule schedule = new Schedule(word);
        scheduleRepository.save(schedule);
    }

    public Word editWord(Word wordDto) {
        Word word = repository.findById(wordDto.getId()).orElseThrow(NotFoundException::new);
        word.setTranslation(wordDto.getTranslation());
        word.setContext(wordDto.getContext());
        word.setExample(wordDto.getExample());
        return repository.save(word);

    }

    public void trainWords(List<Word> words) {
        for (Word word: words) {


        }

    }
}
