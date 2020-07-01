package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.QuizDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Quiz} and its DTO {@link QuizDTO}.
 */
@Mapper(componentModel = "spring", uses = {MatiereMapper.class, QuestionMapper.class})
public interface QuizMapper extends EntityMapper<QuizDTO, Quiz> {

    @Mapping(source = "matiere.id", target = "matiereId")
    QuizDTO toDto(Quiz quiz);

    @Mapping(source = "matiereId", target = "matiere")
    @Mapping(target = "removeQuestion", ignore = true)
    Quiz toEntity(QuizDTO quizDTO);

    default Quiz fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quiz quiz = new Quiz();
        quiz.setId(id);
        return quiz;
    }
}
