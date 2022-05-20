package com.example.ankitrainingsystem.mapper;

import com.example.ankitrainingsystem.dto.NewWordDto;
import com.example.ankitrainingsystem.model.Word;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WordMapper {
    //WordMapper INSTANCE = Mappers.getMapper(WordMapper.class);
    WordMapper MAPPER = Mappers.getMapper( WordMapper.class );

    @Mapping(target = "id", ignore = true)
    Word dtoToWord(NewWordDto dto);

    @Mapping(target = "id", ignore = true)
    void updateWordFromDto(NewWordDto dto, @MappingTarget Word word);
}
