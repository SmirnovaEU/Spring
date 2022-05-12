package com.example.ankitrainingsystem.repository;


import com.example.ankitrainingsystem.model.Dictionary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostFilter;

import java.util.List;


public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Dictionary> findAll();

}
