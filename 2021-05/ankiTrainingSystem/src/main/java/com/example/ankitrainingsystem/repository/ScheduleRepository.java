package com.example.ankitrainingsystem.repository;

import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Schedule;
import com.example.ankitrainingsystem.model.Word;
import com.example.ankitrainingsystem.model.WordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByDictionary(Dictionary dictionary);

    @Query(value = "select s from schedule s where s.dictionary = ?1 and " +
            "s.status <> ?2  order by s.nextTrainDate") //and s.nextTrainDate < ?3
    List<Schedule> findWordsForRepeat(Dictionary dict, WordStatus status, LocalDate currentDate);

    @Query(value = "select s from schedule s where s.dictionary = ?1 and " +
            "s.status = ?2 order by s.nextTrainDate")
    List<Schedule> findWordsForNewTraining(Dictionary dict, WordStatus status);

    Schedule findByWord(Word word);
}
