package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.OptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Option} and its DTO {@link OptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CursusMapper.class})
public interface OptionMapper extends EntityMapper<OptionDTO, Option> {

    @Mapping(source = "quiz.id", target = "quizId")
    OptionDTO toDto(Option option);

    @Mapping(source = "quizId", target = "quiz")
    Option toEntity(OptionDTO optionDTO);

    default Option fromId(Long id) {
        if (id == null) {
            return null;
        }
        Option option = new Option();
        option.setId(id);
        return option;
    }
}
