package com.example.ankitrainingsystem.dto;


import lombok.Data;

import java.time.LocalDate;
import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.model.WordStatus;


@Data
public class NewWordDto {

    private String name;
    private String translation;
    private String context;
    private String example;
    private LocalDate addedDate;
    private WordStatus state;
    private Dictionary dictionary;
}
