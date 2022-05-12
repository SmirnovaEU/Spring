package com.example.ankitrainingsystem.mapper;

import com.example.ankitrainingsystem.dto.NewWordDto;
import com.example.ankitrainingsystem.model.Word;
import org.mapstruct.Mapper;

@Mapper
public interface WordMapper {
    Word dtoToWord(NewWordDto dto);
}
