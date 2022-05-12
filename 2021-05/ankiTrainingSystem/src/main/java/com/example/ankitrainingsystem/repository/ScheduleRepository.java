package com.example.ankitrainingsystem.repository;

import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Schedule;
import com.example.ankitrainingsystem.model.WordStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllByDictionary(Dictionary dictionary);

    @Query(value = "select ?3 s from schedule s where s.dictionary = ?1 and " +
            "s.status = ?2 and s.next_train_date < ?4 order by s.next_train_date")
    List<Schedule> findWordsForRepeat(Dictionary dict, WordStatus status, int quantity, LocalDate currentDate);
}
