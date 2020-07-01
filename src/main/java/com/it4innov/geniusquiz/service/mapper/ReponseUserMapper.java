package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.ReponseUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReponseUser} and its DTO {@link ReponseUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionMapper.class, EvaluationMapper.class, EventuelReponseMapper.class})
public interface ReponseUserMapper extends EntityMapper<ReponseUserDTO, ReponseUser> {

    @Mapping(source = "quizQuestion.id", target = "quizQuestionId")
    @Mapping(source = "evaluation.id", target = "evaluationId")
    ReponseUserDTO toDto(ReponseUser reponseUser);

    @Mapping(source = "quizQuestionId", target = "quizQuestion")
    @Mapping(source = "evaluationId", target = "evaluation")
    @Mapping(target = "removeEventuelReponse", ignore = true)
    ReponseUser toEntity(ReponseUserDTO reponseUserDTO);

    default ReponseUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReponseUser reponseUser = new ReponseUser();
        reponseUser.setId(id);
        return reponseUser;
    }
}
