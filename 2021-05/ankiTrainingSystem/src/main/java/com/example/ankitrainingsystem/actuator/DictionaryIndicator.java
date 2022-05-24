package com.example.ankitrainingsystem.actuator;

import com.example.ankitrainingsystem.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class DictionaryIndicator implements HealthIndicator {
    private final int DICTS_THRESHOLD = 2;
    private final DictionaryRepository repository;

    @Autowired
    public DictionaryIndicator(DictionaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Health health() {
        int dictionaryCount = repository.findAll().size();
        boolean noDictionaries = dictionaryCount < DICTS_THRESHOLD;
        if (noDictionaries) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "There are too few dictionaries!")
                    .build();
        } else {
            return Health.up().withDetail("message", "Cool!").build();
        }
    }
}
