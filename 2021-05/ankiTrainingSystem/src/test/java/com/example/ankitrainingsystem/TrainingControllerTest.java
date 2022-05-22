package com.example.ankitrainingsystem;

import com.example.ankitrainingsystem.controller.WordController;
import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Schedule;
import com.example.ankitrainingsystem.model.Word;
import com.example.ankitrainingsystem.model.WordStatus;
import com.example.ankitrainingsystem.repository.DictionaryRepository;
import com.example.ankitrainingsystem.repository.ScheduleRepository;
import com.example.ankitrainingsystem.repository.WordRepository;
import com.example.ankitrainingsystem.security.UserDetailSecurityService;
import com.example.ankitrainingsystem.service.WordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WordController.class)
public class TrainingControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private DictionaryRepository dictRepository;

    @MockBean
    private WordRepository wordRepository;

    @MockBean
    private WordService wordService;

    @MockBean
    private UserDetailSecurityService userDetail;

    @Test
    void shouldReturnWordsForNewTrainByDict() throws Exception {
        Dictionary dict = new Dictionary();
        dict.setId(1L);
        dictRepository.save(dict);
        Schedule schedule1 = new Schedule(new Word(1, "word1", "translate1", dict));
        Schedule schedule2 = new Schedule(new Word(2, "word2", "translate2", dict));
        List<Schedule> scheduleList = List.of(schedule1, schedule2);
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        given(scheduleRepository.findWordsForNewTraining(dict, WordStatus.NEW)).willReturn(scheduleList);

        mvc.perform(get("/trainings/new").param("dictId", "1"))
                .andExpect(status().is3xxRedirection());
    }
}
