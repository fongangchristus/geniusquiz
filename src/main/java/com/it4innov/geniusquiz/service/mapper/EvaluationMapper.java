package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.EvaluationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Evaluation} and its DTO {@link EvaluationDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuizMapper.class})
public interface EvaluationMapper extends EntityMapper<EvaluationDTO, Evaluation> {

    @Mapping(source = "quiz.id", target = "quizId")
    EvaluationDTO toDto(Evaluation evaluation);

    @Mapping(source = "quizId", target = "quiz")
    Evaluation toEntity(EvaluationDTO evaluationDTO);

    default Evaluation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Evaluation evaluation = new Evaluation();
        evaluation.setId(id);
        return evaluation;
    }
}
