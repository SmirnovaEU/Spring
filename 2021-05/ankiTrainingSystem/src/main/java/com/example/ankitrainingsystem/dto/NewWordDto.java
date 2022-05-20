package com.example.ankitrainingsystem.dto;


import lombok.Data;

import com.example.ankitrainingsystem.model.Dictionary;

import java.time.LocalDate;


@Data
public class NewWordDto {

    private String name;
    private String translation;
    private String context;
    private String example;
//    private LocalDate addedDate;
//    private WordStatus state;
    private Dictionary dictionary;
}
