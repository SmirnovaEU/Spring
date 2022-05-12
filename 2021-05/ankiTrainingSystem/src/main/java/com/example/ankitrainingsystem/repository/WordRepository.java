package com.example.ankitrainingsystem.repository;


import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Word;
import com.example.ankitrainingsystem.model.WordStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long> {
    List<Word> findAllByDictionary(Dictionary dictionary);

    @Query(value = "select ?3 w from words w where w.dictionary = ?1 and " +
            "w.status = ?2 order by w.add_date")
    List<Word> findWordsForNewTraining(Dictionary dict, WordStatus status, int quantity);




}
