package com.example.ankitrainingsystem.repository;


import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Word;
import com.example.ankitrainingsystem.model.WordStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {
    List<Word> findAllByDictionary(Dictionary dictionary);

}
