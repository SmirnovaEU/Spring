package com.example.ankitrainingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnkiTrainingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnkiTrainingSystemApplication.class, args);
    }

}
