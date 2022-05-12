package com.example.ankitrainingsystem.repository;

import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.Training;
import com.example.ankitrainingsystem.model.Word;
import com.example.ankitrainingsystem.model.WordStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingRepository extends CrudRepository<Training, Long> {

}
