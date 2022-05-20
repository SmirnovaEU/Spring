package com.example.ankitrainingsystem.repository;

import com.example.ankitrainingsystem.model.Result;
import com.example.ankitrainingsystem.model.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {

    List<Result> findAllByTraining(Training training);

}
