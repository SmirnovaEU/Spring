package com.example.ankitrainingsystem;

import com.example.ankitrainingsystem.controller.WordController;
import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Schedule;
import com.example.ankitrainingsystem.model.Word;
import com.example.ankitrainingsystem.repository.DictionaryRepository;
import com.example.ankitrainingsystem.repository.ScheduleRepository;
import com.example.ankitrainingsystem.repository.WordRepository;
import com.example.ankitrainingsystem.security.UserDetailSecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WordController.class)
public class WordControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private WordRepository repository;

    @MockBean
    private DictionaryRepository dictRepository;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    UserDetailSecurityService userService;

    @MockBean
    WordController wordController;

    @Test
    void shouldReturnCorrectWordsListByDict() throws Exception {
        Dictionary dict = new Dictionary();
        dict.setId(1L);
        dictRepository.save(dict);
        Word word1 = new Word(1, "word1", "translate1", dict);
        Word word2 = new Word(2, "word2", "translate2", dict);
        List<Word> words = List.of(word1, word2);
        repository.save(word1);
        repository.save(word2);
        given(repository.findAllByDictionary(dict)).willReturn(words);

        mvc.perform(get("/words/all"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void shouldDeleteById() throws Exception{
        Dictionary dict = new Dictionary();
        Word word = new Word(1, "word1", "translate1", dict);
        given(repository.findById(1L)).willReturn(Optional.of(word));
        repository.deleteById(1L);
        given(repository.findById(1L)).willReturn(null);

        mvc.perform(get("/words/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    void shouldReturnScheduleByDict() throws Exception{
        Dictionary dict = new Dictionary();
        dict.setId(1L);
        dictRepository.save(dict);
        Schedule schedule1 = new Schedule(new Word(1, "word1", "translate1", dict));
        Schedule schedule2 = new Schedule(new Word(2, "word2", "translate2", dict));
        List<Schedule> scheduleList = List.of(schedule1, schedule2);
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        given(scheduleRepository.findAllByDictionary(dict)).willReturn(scheduleList);

        mvc.perform(get("/words/schedule"))
                .andExpect(status().is3xxRedirection());

    }
}
