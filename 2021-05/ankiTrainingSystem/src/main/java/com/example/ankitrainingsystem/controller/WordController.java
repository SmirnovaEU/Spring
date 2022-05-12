package com.example.ankitrainingsystem.controller;

import com.example.ankitrainingsystem.dto.NewWordDto;
import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Schedule;
import com.example.ankitrainingsystem.model.Word;
import com.example.ankitrainingsystem.repository.DictionaryRepository;
import com.example.ankitrainingsystem.repository.ScheduleRepository;
import com.example.ankitrainingsystem.repository.WordRepository;

import com.example.ankitrainingsystem.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class WordController {

    private final WordRepository repository;
    private final DictionaryRepository dictRepository;
    private final WordService service;
    private final ScheduleRepository scheduleRepository;


    @Autowired
    public WordController(WordRepository repository, DictionaryRepository dictRepository, WordService service, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.dictRepository = dictRepository;
        this.service = service;
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping("/words/all")
    public String dictionaryPage(Model model) {
        Dictionary dict = dictRepository.findById(1L).orElseThrow(NotFoundException::new);
        List<Word> dictWords = repository.findAllByDictionary(dict);
        model.addAttribute("dictionary", dict);
        model.addAttribute("words", dictWords);
        return "dict";
    }

    @GetMapping("words/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Word word = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("word", word);
        return "edit";
    }

    @GetMapping("/words/addWord")
    public String editNewPage(@RequestParam("id") long dictId, Model model) {
        NewWordDto wordForm = new NewWordDto();

        Dictionary dictionary = dictRepository.findById(dictId).orElseThrow(NotFoundException::new);

        model.addAttribute("wordForm", wordForm);
        model.addAttribute("dictionary", dictionary);
        return "add";
    }

    @PostMapping("/words/edit")
    public String editWord(
            Word wordForm,
            Model model
    ) {
        Word saved = service.editWord(wordForm);
        model.addAttribute(saved);
        return "redirect:/words/all";
    }

    @PostMapping("/words/addWord")
    public String createWord(@RequestParam("dictId") long dictId, NewWordDto wordForm, Model model) {
        service.createWord(wordForm, dictId);
        return "redirect:/words/all";
    }

    @GetMapping("/words/delete")
    public String deleteWord(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/words/all";
    }

    @GetMapping("/words/schedule")
    public String schedulePage(Model model) {
        //TODO: заменить на текущий словарь, id словаря должен быть в модели
        Dictionary dict = dictRepository.findById(1L).orElseThrow(NotFoundException::new);
        List<Schedule> dictSchedule = scheduleRepository.findAllByDictionary(dict);
        model.addAttribute("dictionary", dict);
        model.addAttribute("schedules", dictSchedule);
        return "schedule";
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNFE(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

}
