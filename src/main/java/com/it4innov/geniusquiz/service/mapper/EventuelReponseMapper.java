package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.EventuelReponseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventuelReponse} and its DTO {@link EventuelReponseDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuizMapper.class})
public interface EventuelReponseMapper extends EntityMapper<EventuelReponseDTO, EventuelReponse> {

    @Mapping(source = "quiz.id", target = "quizId")
    EventuelReponseDTO toDto(EventuelReponse eventuelReponse);

    @Mapping(source = "quizId", target = "quiz")
    @Mapping(target = "reponseUsers", ignore = true)
    @Mapping(target = "removeReponseUser", ignore = true)
    EventuelReponse toEntity(EventuelReponseDTO eventuelReponseDTO);

    default EventuelReponse fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventuelReponse eventuelReponse = new EventuelReponse();
        eventuelReponse.setId(id);
        return eventuelReponse;
    }
}
